package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.DoctorRequest;
import dev.patika.VetAPI.dto.response.DoctorResponse;
import dev.patika.VetAPI.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    Doctor asEntity(DoctorRequest doctorRequest);
    DoctorResponse asResponse(Doctor doctor);
    List<DoctorResponse> asResponseList(List<Doctor> doctor);

    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "availableDates", ignore = true)
    void update(@MappingTarget Doctor entity, DoctorRequest request);
}
