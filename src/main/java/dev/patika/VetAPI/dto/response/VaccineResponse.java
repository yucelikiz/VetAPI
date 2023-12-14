package dev.patika.VetAPI.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineResponse {
    private Long id;
    private String name;
    private String code;
    private String protectionStartDate;
    private String protectionEndDate;
}