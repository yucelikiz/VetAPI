package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.AnimalRequest;
import dev.patika.VetAPI.dto.response.AnimalResponse;
import dev.patika.VetAPI.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper
public interface AnimalMapper {

    @Mapping(target = "customer.id", source = "customerId")
    Animal asEntity(AnimalRequest animalRequest);
    AnimalResponse asResponse(Animal animal);
    List<AnimalResponse> asOutput(List<Animal> animals);
    void update(@MappingTarget Animal entity, AnimalRequest animalRequest);
}
