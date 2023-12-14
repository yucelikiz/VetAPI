package dev.patika.VetAPI.controller;

import dev.patika.VetAPI.dto.request.CustomerRequest;
import dev.patika.VetAPI.dto.response.CustomerResponse;
import dev.patika.VetAPI.entity.Customer;
import dev.patika.VetAPI.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> findAll() {return customerService.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getById(@PathVariable("id") Long id) {return customerService.getById(id);}

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getByName(@PathVariable("name") String name) {return customerService.getByName(name);}

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse save(@RequestBody CustomerRequest customer) {
        return customerService.create(customer);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse update(@PathVariable("id") Long id, @RequestBody CustomerRequest updatedCostumer) {
        return customerService.update(id, updatedCostumer);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {customerService.deleteById(id);}
}
