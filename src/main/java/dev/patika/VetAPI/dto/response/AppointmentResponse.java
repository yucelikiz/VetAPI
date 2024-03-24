package dev.patika.VetAPI.dto.response;

import dev.patika.VetAPI.entity.Animal;
import dev.patika.VetAPI.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentResponse {
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTimeStart;
    private LocalTime appointmentTimeEnd;
    private Doctor doctor;
    private Animal animal;
}
