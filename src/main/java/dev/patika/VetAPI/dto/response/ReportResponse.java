package dev.patika.VetAPI.dto.response;

import dev.patika.VetAPI.entity.Appointment;
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
    private Appointment appointment;
    private Long appointmentId;

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment; // appointment alanı atandı
        this.appointmentId = (appointment != null) ? appointment.getId() : null;
    }
}

