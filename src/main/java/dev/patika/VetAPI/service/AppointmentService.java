package dev.patika.VetAPI.service;

import dev.patika.VetAPI.dto.request.AppointmentRequest;
import dev.patika.VetAPI.dto.response.AppointmentResponse;
import dev.patika.VetAPI.entity.Appointment;
import dev.patika.VetAPI.entity.Doctor;
import dev.patika.VetAPI.mapper.AppointmentMapper;
import dev.patika.VetAPI.repository.AppointmentRepo;
import dev.patika.VetAPI.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentMapper appointmentMapper;

    public List<AppointmentResponse> findAll() {
        List<Appointment> appointmentList = appointmentRepo.findAll();
        return appointmentMapper.asResponseList(appointmentList);
    }

    public AppointmentResponse getById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "id'li randevu bulunamadı!"));
        return appointmentMapper.asResponseWithIds(appointment);
    }

    public List<AppointmentResponse> getAppointmentsByDateRangeAndDoctors(LocalDate startDate, LocalDate endDate, String doctorName) {
        List<Appointment> appointments = appointmentRepo.findAppointmentsByDateRangeAndDoctors(startDate, endDate, doctorName);
        return appointmentMapper.asResponseList(appointments);
    }

    public List<AppointmentResponse> getAppointmentsByDateRangeAndAnimals(LocalDate startDate, LocalDate endDate, String animalName ) {
        List<Appointment> appointments = appointmentRepo.findAppointmentsByDateRangeAndAnimals(startDate, endDate, animalName);
        return appointmentMapper.asResponseList(appointments);
    }

    public AppointmentResponse create(AppointmentRequest appointmentRequest) {
        // Check if the doctor is available on the requested date
        checkDoctorAvailability(appointmentRequest);

        // Check if there is an overlapping appointment
        checkForAppointmentOverlap(appointmentRequest);

        Appointment savedAppointment = appointmentRepo.save(appointmentMapper.fromRequestWithIds(appointmentRequest));
        return appointmentMapper.asResponseWithIds(savedAppointment);
    }

    private void checkDoctorAvailability(AppointmentRequest appointmentRequest) {
        Long doctorId = appointmentRequest.getDoctorId();
        // Check if the doctor ID is provided
        if (doctorId == null) {
            throw new IllegalArgumentException("Doktor ID boş olamaz!");
        }
        // Retrieve the doctor with available dates by ID
        Doctor doctor = doctorRepo.findAvailableDatesById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı!"));
        LocalDate requestedDate = appointmentRequest.getAppointmentDate();

        // Check if the requested date is in the list of available dates
        boolean isDateAvailable = doctor.getAvailableDates().stream()
                .anyMatch(availableDate -> availableDate.getAvailableDate().isEqual(requestedDate));

        if (!isDateAvailable) {
            throw new RuntimeException("Doktor bu tarihte müsait değil!");
        }
    }

    private void checkForAppointmentOverlap(AppointmentRequest newAppointment) {
        List<Appointment> overlappingAppointments = appointmentRepo.findOverlappingAppointments(
                newAppointment.getDoctorId(),
                newAppointment.getAppointmentDate(),
                newAppointment.getAppointmentTimeStart(),
                newAppointment.getAppointmentTimeEnd()
        );

        if (!overlappingAppointments.isEmpty()) {
            throw new RuntimeException("Bu saat başka bir randevuya ayrılmış!");
        }
    }

    public AppointmentResponse update(Long id, AppointmentRequest updatedAppointment) {
        Optional<Appointment> optionalExistingAppointment = appointmentRepo.findById(id);

        if (optionalExistingAppointment.isEmpty()) {
            throw new RuntimeException(id + "id'li randevu bulunamadı!");
        }
        Appointment existingAppointment = optionalExistingAppointment.get();
        appointmentMapper.update(existingAppointment, updatedAppointment);
        Appointment updatedEntity = appointmentRepo.save(existingAppointment);
        return appointmentMapper.asResponseWithIds(updatedEntity);
    }

    public void deleteById(Long id) {
        Optional<Appointment> isAppointmentExist = appointmentRepo.findById(id);
        if (isAppointmentExist.isPresent()) {
            appointmentRepo.delete(isAppointmentExist.get());
        } else {
            throw new RuntimeException(id + "id'li randevu bulunamadı!");
        }
    }
}
