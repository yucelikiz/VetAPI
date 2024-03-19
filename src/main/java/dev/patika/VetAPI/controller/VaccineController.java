package dev.patika.VetAPI.controller;

import dev.patika.VetAPI.dto.request.VaccineRequest;
import dev.patika.VetAPI.dto.response.AnimalResponse;
import dev.patika.VetAPI.dto.response.VaccineResponse;
import dev.patika.VetAPI.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccine")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> findAll() {return vaccineService.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse getById(@PathVariable("id") Long id) {return vaccineService.getById(id);}

    @GetMapping("/animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> getByAnimalId(@PathVariable("animalId") Long animalId) {return vaccineService.getByAnimalId(animalId);}

    @GetMapping("/findAnimalsByVaccineProtectionEndDateBetween")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAnimalsByVaccineProtectionEndDateBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return vaccineService.findAnimalsByVaccineProtectionEndDateBetween(startDate, endDate);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse save(@RequestBody VaccineRequest vaccine) {
        return vaccineService.create(vaccine);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse update(@PathVariable Long id, @RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.update(id, vaccineRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {vaccineService.deleteById(id);}
}
