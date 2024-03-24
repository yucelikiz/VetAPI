package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.CustomerRequest;
import dev.patika.VetAPI.dto.response.CustomerResponse;
import dev.patika.VetAPI.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {
    Customer asEntity(CustomerRequest customerRequest);
    CustomerResponse asOutput(Customer customer);
    List<CustomerResponse> asResponse(List<Customer> customerList);

    void update(@MappingTarget Customer entity, CustomerRequest request);
}
