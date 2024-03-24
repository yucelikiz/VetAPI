package dev.patika.VetAPI.service;

import dev.patika.VetAPI.dto.request.CustomerRequest;
import dev.patika.VetAPI.dto.response.CustomerResponse;
import dev.patika.VetAPI.mapper.CustomerMapper;
import dev.patika.VetAPI.repository.CustomerRepo;
import dev.patika.VetAPI.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAll() {return customerMapper.asResponse(customerRepo.findAll());}

    public CustomerResponse getById(Long id) {
        return customerMapper.asOutput(customerRepo.findById(id).orElseThrow(() -> new RuntimeException(id+"'li müşteri bulunamadı!")));
    }

    /* public CustomerResponse getByName(String name) {
        return customerMapper.asOutput(customerRepo.findByName(name).orElseThrow(() -> new RuntimeException(name + "isimli müşteri bulunamadı!")));
    } */

    public List<CustomerResponse> getByName(String name) {
        List<Customer> customers = customerRepo.findByNameIgnoreCase(name);
        if(customers.isEmpty()) {
            throw new RuntimeException(name + " isimli müşteri bulunamadı!");
        }

        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : customers) {
            customerResponses.add(customerMapper.asOutput(customer));
        }

        return customerResponses;
    }



    public CustomerResponse create(CustomerRequest request) {
        Optional<Customer> isCustomerExist = customerRepo.findByMail(request.getMail());

        if (isCustomerExist.isEmpty()) {
            Customer customerSaved = customerRepo.save(customerMapper.asEntity(request));
            return customerMapper.asOutput(customerSaved);
        }
        throw new RuntimeException("Bu müşterinin kaydı daha önce yapılmıştır!");
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        Optional<Customer> optionalExistingCustomer = customerRepo.findById(id);
        Optional<Customer> isCustomerExist = customerRepo.findByMail(request.getMail());

        if (optionalExistingCustomer.isEmpty()) {
            throw new RuntimeException(id + "'li müşteri veritabanında bulunamadı!");
        }
        if (isCustomerExist.isPresent() && !isCustomerExist.get().getId().equals(id)) {
            throw new RuntimeException(request.getMail() + "mail adresi ile kayıtlı başka bir müşteri bulunmaktadır!");
        }
        Customer customer = optionalExistingCustomer.get();
        // Use the customerMapper to update the existing Customer entity
        customerMapper.update(customer, request);
        // Map the updated entity to the response
        return customerMapper.asOutput(customerRepo.save(customer));
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
