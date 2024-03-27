package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.AppointmentRequest;
import dev.patika.VetAPI.dto.response.AppointmentResponse;
import dev.patika.VetAPI.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    /* @Mapping(target = "doctor.id", source = "doctorId")
    @Mapping(target = "animal.id", source = "animalId")
    Appointment asEntity(AppointmentRequest request);
    AppointmentResponse asResponse(Appointment appointment);
    List<AppointmentResponse> asResponseList(List<Appointment> appointments);
    void update(@MappingTarget Appointment entity, AppointmentRequest request); */


    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(target = "doctor.id", source = "doctorId")
    @Mapping(target = "animal.id", source = "animalId")
    Appointment asEntity(AppointmentRequest appointmentRequest);

    @Mapping(target = "appointmentTimeStart", source = "appointmentTimeStart")
    @Mapping(target = "appointmentTimeEnd", source = "appointmentTimeEnd")
    AppointmentResponse asResponse(Appointment appointment);

    List<AppointmentResponse> asResponseList(List<Appointment> appointments);


    @Mapping(target = "appointmentTimeStart", source = "appointmentTimeStart")
    @Mapping(target = "appointmentTimeEnd", source = "appointmentTimeEnd")
    @Named("asResponseWithIds")
    AppointmentResponse asResponseWithIds(Appointment appointment);

    @Mapping(target = "doctor.id", source = "doctorId")
    @Mapping(target = "animal.id", source = "animalId")
    @Mapping(target = "appointmentTimeStart", source = "appointmentTimeStart")
    @Mapping(target = "appointmentTimeEnd", source = "appointmentTimeEnd")
    Appointment fromRequestWithIds(AppointmentRequest request);

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "animal", ignore = true)
    void update(@MappingTarget Appointment entity, AppointmentRequest request);

    default LocalTime mapToLocalTime(String time) {
        return time != null ? LocalTime.parse(time) : null;
    }
}
