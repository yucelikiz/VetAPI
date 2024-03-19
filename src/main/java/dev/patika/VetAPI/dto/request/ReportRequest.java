package dev.patika.VetAPI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportRequest {
    private String title;
    private String diagnosis;
    private Double price;
    private Long appointmentId;
    private List<Long> vaccineIds;
}
