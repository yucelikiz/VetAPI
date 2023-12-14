package dev.patika.VetAPI.controller;

import dev.patika.VetAPI.dto.request.AvailableDateRequest;
import dev.patika.VetAPI.dto.response.AvailableDateResponse;
import dev.patika.VetAPI.service.AvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/availabledate")
@RequiredArgsConstructor
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateResponse> findAll() {
        return availableDateService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse getById(@PathVariable("id") Long id) {
        return availableDateService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse save(@RequestBody AvailableDateRequest request) {
        return availableDateService.create(request);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse update(@PathVariable("id") Long id, @RequestBody AvailableDateRequest updatedRequest) {
        return availableDateService.update(id, updatedRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        availableDateService.deleteById(id);
    }
}
