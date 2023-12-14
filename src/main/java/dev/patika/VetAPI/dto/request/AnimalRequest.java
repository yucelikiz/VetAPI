package dev.patika.VetAPI.dto.request;

import dev.patika.VetAPI.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalRequest {
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String color;
    private LocalDate birthDate;
    private Long customerId;
}
