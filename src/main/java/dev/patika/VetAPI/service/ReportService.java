package dev.patika.VetAPI.service;

import dev.patika.VetAPI.dto.request.ReportRequest;
import dev.patika.VetAPI.dto.response.ReportResponse;
import dev.patika.VetAPI.entity.Appointment;
import dev.patika.VetAPI.entity.Report;
import dev.patika.VetAPI.entity.Vaccine;
import dev.patika.VetAPI.mapper.ReportMapper;
import dev.patika.VetAPI.repository.AppointmentRepo;
import dev.patika.VetAPI.repository.ReportRepo;
import dev.patika.VetAPI.repository.VaccineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepo reportRepository;
    private final ReportMapper reportMapper;
    private final AppointmentRepo appointmentRepository;
    private final VaccineRepo vaccineRepository;

    public List<ReportResponse> findAll() {
        List<Report> reports = reportRepository.findAll();
        return reportMapper.asResponseList(reports);
    }

    public ReportResponse getById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapor bulunamadı!"));
        return reportMapper.asResponse(report);
    }

    public ReportResponse create(ReportRequest request) {
        // Rapor için kullanılacak Appointment nesnesini bul
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Randevu bulunamadı!"));

        // Rapor için kullanılacak Vaccine nesnelerini bul
        List<Vaccine> vaccines = vaccineRepository.findAllById(request.getVaccineIds());

        // Yeni Rapor nesnesi oluştur
        Report report = new Report();
        report.setTitle(request.getTitle());
        report.setDiagnosis(request.getDiagnosis());
        report.setPrice(request.getPrice());
        report.setAppointment(appointment);
        report.setVaccines(vaccines);

        // Raporu kaydet
        Report savedReport = reportRepository.save(report);

        // ReportResponse olarak dönüştür ve geri dön
        return reportMapper.asResponse(savedReport);
    }

    public ReportResponse update(Long id, ReportRequest request) {
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapor bulunamadı!"));
        reportMapper.update(existingReport, request);
        Report updatedReport = reportRepository.save(existingReport);
        return reportMapper.asResponse(updatedReport);
    }

    public void deleteById(Long id) {
        reportRepository.deleteById(id);
    }
}
