package dev.patika.VetAPI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentRequest {
    private LocalDate appointmentDate;
    private LocalTime appointmentTimeStart;
    private LocalTime appointmentTimeEnd;
    private Long doctorId;
    private Long animalId;
}
