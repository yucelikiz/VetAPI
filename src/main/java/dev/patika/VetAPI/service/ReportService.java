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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepo reportRepo;
    private final ReportMapper reportMapper;
    private final AppointmentRepo appointmentRepo;

    public List<ReportResponse> findAll() {
        return reportMapper.asResponseList(reportRepo.findAll());
    }

    public ReportResponse getById(Long id) {
        Report report = reportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapor bulunamadı!"));
        return reportMapper.asResponse(report);
    }

    public ReportResponse create(ReportRequest request) {
        Optional<Report> isReportExist = reportRepo.findByTitle(request.getTitle());

        if (isReportExist.isEmpty()) {
            Report savedReport = reportRepo.save(reportMapper.asEntity(request));
            return reportMapper.asResponse(savedReport);
        }
        throw new RuntimeException("Bu başlıkta bir rapor daha önce veritabanına kaydedilmiştir!");
    }

    public ReportResponse update(Long id, ReportRequest request) {
        Report existingReport = reportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapor bulunamadı!"));
        reportMapper.update(existingReport, request);
        Report updatedReport = reportRepo.save(existingReport);
        return reportMapper.asResponse(updatedReport);
    }

    public void deleteById(Long id) {
        reportRepo.deleteById(id);
    }
}
