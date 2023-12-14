package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.AvailableDateRequest;
import dev.patika.VetAPI.dto.response.AvailableDateResponse;
import dev.patika.VetAPI.entity.AvailableDate;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvailableDateMapper {
    AvailableDateMapper INSTANCE = Mappers.getMapper(AvailableDateMapper.class);

    AvailableDate asEntity(AvailableDateRequest request);
    AvailableDateResponse asResponse(AvailableDate availableDate);
    List<AvailableDateResponse> asResponseList(List<AvailableDate> availableDates);

    @Mapping(target = "doctorId", source = "doctor.id")
    @Named("asResponseWithDoctorId")
    AvailableDateResponse asResponseWithDoctorId(AvailableDate availableDate);

    @Mapping(target = "doctor.id", source = "doctorId")
    AvailableDate fromRequestWithDoctorId(AvailableDateRequest request);

    @Mapping(target = "doctor", ignore = true)
    void update(@MappingTarget AvailableDate entity, AvailableDateRequest request);
}
