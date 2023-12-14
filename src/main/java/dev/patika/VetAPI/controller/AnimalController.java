package dev.patika.VetAPI.controller;

import dev.patika.VetAPI.dto.request.AnimalRequest;
import dev.patika.VetAPI.dto.response.AnimalResponse;
import dev.patika.VetAPI.entity.Animal;
import dev.patika.VetAPI.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAll() {return animalService.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getById(@PathVariable("id") Long id) {return animalService.getById(id);}

    @GetMapping("/getByCustomer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findByCustomerId(@PathVariable("customerId") Long customerId) {
        return animalService.findByCustomerId(customerId);
    }

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getByName(@PathVariable("name") String name) {return animalService.getByName(name);}

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse save(@RequestBody AnimalRequest animal) {
        return animalService.create(animal);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse update(@PathVariable Long id, @RequestBody AnimalRequest animalRequest) {
        return animalService.update(id, animalRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {animalService.deleteById(id);}

}
