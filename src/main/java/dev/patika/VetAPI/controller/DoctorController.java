package dev.patika.VetAPI.controller;

import dev.patika.VetAPI.dto.request.DoctorRequest;
import dev.patika.VetAPI.dto.response.DoctorResponse;
import dev.patika.VetAPI.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse getById(@PathVariable("id") Long id) {
        return doctorService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponse save(@RequestBody DoctorRequest request) {
        return doctorService.create(request);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse update(@PathVariable("id") Long id, @RequestBody DoctorRequest updatedRequest) {
        return doctorService.update(id, updatedRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        doctorService.deleteById(id);
    }
}
