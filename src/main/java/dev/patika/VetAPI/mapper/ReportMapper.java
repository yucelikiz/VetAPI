package dev.patika.VetAPI.mapper;

import dev.patika.VetAPI.dto.request.ReportRequest;
import dev.patika.VetAPI.dto.response.ReportResponse;
import dev.patika.VetAPI.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    Report asEntity(ReportRequest request);
    ReportResponse asResponse(Report report);
    List<ReportResponse> asResponseList(List<Report> reports);

    void update(@MappingTarget Report report, ReportRequest request);
}
