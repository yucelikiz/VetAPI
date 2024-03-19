package dev.patika.VetAPI.controller;

import dev.patika.VetAPI.dto.request.ReportRequest;
import dev.patika.VetAPI.dto.response.ReportResponse;
import dev.patika.VetAPI.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportResponse> findAll() {
        return reportService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse getById(@PathVariable("id") Long id) {
        return reportService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ReportResponse save(@RequestBody ReportRequest request) {
        return reportService.create(request);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse update(@PathVariable("id") Long id, @RequestBody ReportRequest request) {
        return reportService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        reportService.deleteById(id);
    }
}
