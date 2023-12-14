package dev.patika.VetAPI.service;

import dev.patika.VetAPI.dto.request.CustomerRequest;
import dev.patika.VetAPI.dto.response.CustomerResponse;
import dev.patika.VetAPI.entity.Animal;
import dev.patika.VetAPI.mapper.CustomerMapper;
import dev.patika.VetAPI.repository.CustomerRepo;
import dev.patika.VetAPI.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAll() {return customerMapper.asOutput(customerRepo.findAll());}

    public CustomerResponse getById(Long id) {
        return customerMapper.asOutput(customerRepo.findById(id).orElseThrow(() -> new RuntimeException(id+"'li müşteri bulunamadı!")));
    }

    public CustomerResponse getByName(String name) {
        return customerMapper.asOutput(customerRepo.findByName(name).orElseThrow(() -> new RuntimeException(name + "isimli müşteri bulunamadı!")));
    }

    public CustomerResponse create(CustomerRequest customerRequest) {
        Optional<Customer> isCustomerExist = customerRepo.findByName(customerRequest.getName());

        if (isCustomerExist.isEmpty()) {
            Customer customerSaved = customerRepo.save(customerMapper.asEntity(customerRequest));
            return customerMapper.asOutput(customerSaved);
        }
        throw new RuntimeException("Bu müşterinin kaydı daha önce yapılmıştır!");
    }

    public CustomerResponse update(Long id, CustomerRequest updatedCustomer) {
        Optional<Customer> optionalExistingCustomer = customerRepo.findById(id);

        if (optionalExistingCustomer.isEmpty()) {
            throw new RuntimeException(id + "'li müşteri veritabanında bulunamadı!");
        }
        Customer existingCustomer = optionalExistingCustomer.get();
        // Use the customerMapper to update the existing Customer entity
        customerMapper.update(existingCustomer, updatedCustomer);
        // Save the updated customer entity
        Customer updatedEntity = customerRepo.save(existingCustomer);
        // Map the updated entity to the response
        return customerMapper.asOutput(updatedEntity);
    }

    public void deleteById(Long id) {
        Optional<Customer> isCustomerExist = customerRepo.findById(id);
        if (isCustomerExist.isPresent()) {
            customerRepo.delete(isCustomerExist.get());
        } else {
            throw new RuntimeException(id + "'li müşteri veritabanında bulunamadı!");
        }
    }
}
