package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.ReportRequest;
import dev.patika.VetAPI.dto.response.ReportResponse;
import dev.patika.VetAPI.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper
public interface ReportMapper {


    @Mapping(target = "appointment.id", source = "appointmentId")
    Report asEntity(ReportRequest reportRequest);
    @Mapping(target = "appointment", source = "appointment")
    ReportResponse asResponse(Report report);
    List<ReportResponse> asResponseList(List<Report> reports);
    void update(@MappingTarget Report entity, ReportRequest reportRequest);
}
