package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.AnimalRequest;
import dev.patika.VetAPI.dto.response.AnimalResponse;
import dev.patika.VetAPI.entity.Animal;
import dev.patika.VetAPI.entity.Customer;
import dev.patika.VetAPI.service.CustomerService;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface AnimalMapper {
    CustomerService customerService = null;

    @Mapping(target = "birthDate", source = "animalRequest.birthDate")
    @Mapping(target = "customer", source = "animalRequest.customerId", qualifiedByName = "mapCustomerIdToCustomer")
    Animal asEntity(AnimalRequest animalRequest);

    void update(@MappingTarget Animal entity, AnimalRequest animalRequest);

    @Named("mapCustomerIdToCustomer")
    default Customer mapCustomerIdToCustomer(Long customerId) {
        if (customerId == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
    }

    AnimalResponse asOutput(Animal animal);

    List<AnimalResponse> asOutput(List<Animal> animals);

}
