package dev.patika.VetAPI.controller;

import dev.patika.VetAPI.dto.request.AppointmentRequest;
import dev.patika.VetAPI.dto.response.AppointmentResponse;
import dev.patika.VetAPI.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getById(@PathVariable("id") Long id) {
        return appointmentService.getById(id);
    }

    @GetMapping("/getAppointmentsByDateRangeAndDoctors")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAppointmentsByDateRangeAndDoctors(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam List<Long> doctorIds) {
        return appointmentService.getAppointmentsByDateRangeAndDoctors(startDate, endDate, doctorIds);
    }

    @GetMapping("/getAppointmentsByDateRangeAndAnimals")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAppointmentsByDateRangeAndAnimals(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam List<Long> animalIds) {
        return appointmentService.getAppointmentsByDateRangeAndAnimals(startDate, endDate, animalIds);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse save(@RequestBody AppointmentRequest appointment) {
        return appointmentService.create(appointment);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse update(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.update(id, appointmentRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        appointmentService.deleteById(id);
    }
}
