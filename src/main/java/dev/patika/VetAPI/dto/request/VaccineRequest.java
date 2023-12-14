package dev.patika.VetAPI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineRequest {
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionEndDate;
    private Long animalId;
}
