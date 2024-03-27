package dev.patika.VetAPI.service;

import dev.patika.VetAPI.dto.request.DoctorRequest;
import dev.patika.VetAPI.dto.response.DoctorResponse;
import dev.patika.VetAPI.entity.Doctor;
import dev.patika.VetAPI.mapper.DoctorMapper;
import dev.patika.VetAPI.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;

    public List<DoctorResponse> findAll() {
        List<Doctor> doctorList = doctorRepo.findAll();
        return doctorMapper.asResponseList(doctorList);
    }

    public DoctorResponse getById(Long id) {
        Doctor doctor = doctorRepo.findById(id).orElseThrow(()-> new RuntimeException(id + "id'li doktor bulunamadı!"));
        return doctorMapper.asResponse(doctor);
    }

    public List<DoctorResponse> getByName(String name) {
        return doctorMapper.asResponseList(doctorRepo.findByName(name));
    }

    public DoctorResponse create(DoctorRequest doctorRequest) {
        Optional<Doctor> isDoctorExist = doctorRepo.findByNameAndMail(
                doctorRequest.getName(),
                doctorRequest.getMail()
        );

        if (isDoctorExist.isEmpty()) {
            Doctor doctorSaved = doctorRepo.save(doctorMapper.asEntity(doctorRequest));
            return doctorMapper.asResponse(doctorSaved);
        }
        throw new RuntimeException("Bu doktorun kaydı daha önce yapılmıştır!");
    }

    public DoctorResponse update(Long id, DoctorRequest updatedDoctor) {
        Optional<Doctor> optionalExistingDoctor = doctorRepo.findById(id);

        if (optionalExistingDoctor.isEmpty()) {
            throw new RuntimeException(id + "'li doktor veritabanında bulunamadı!");
        }
        Doctor existingDoctor = optionalExistingDoctor.get();
        doctorMapper.update(existingDoctor, updatedDoctor); // Use the doctorMapper to update the existing Doctor entity
        Doctor updatedEntity = doctorRepo.save(existingDoctor); // Save the updated doctor entity
        return doctorMapper.asResponse(updatedEntity); // Map the updated entity to the response
    }

    public void deleteById(Long id) {
        Optional<Doctor> isDoctorExist = doctorRepo.findById(id);
        if (isDoctorExist.isPresent()) {
            doctorRepo.delete(isDoctorExist.get());
        } else {
            throw new RuntimeException(id + "'li doktor veritabanında bulunamadı!");
        }
    }
}
