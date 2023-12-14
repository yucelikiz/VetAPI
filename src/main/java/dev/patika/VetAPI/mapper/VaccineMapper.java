package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.VaccineRequest;
import dev.patika.VetAPI.dto.response.VaccineResponse;
import dev.patika.VetAPI.entity.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VaccineMapper {
    VaccineMapper INSTANCE = Mappers.getMapper(VaccineMapper.class);

    Vaccine asEntity(VaccineRequest vaccineRequest);
    VaccineResponse asResponse(Vaccine vaccine);
    List<VaccineResponse> asResponseList(List<Vaccine> vaccines);

    @Mapping(target = "animalId", source = "animal.id")
    @Named("asResponseWithAnimalId")
    VaccineResponse asResponseWithAnimalId(Vaccine vaccine);

    @Mapping(target = "animal.id", source = "animalId")
    Vaccine fromRequestWithAnimalId(VaccineRequest request);

    @Mapping(target = "animal", ignore = true)
    void update(@MappingTarget Vaccine entity, VaccineRequest request);
}
