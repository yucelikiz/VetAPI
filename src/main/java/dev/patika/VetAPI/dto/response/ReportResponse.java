package dev.patika.VetAPI.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private Double price;
    private Long appointmentId;
    private List<Long> vaccineIds;
}
