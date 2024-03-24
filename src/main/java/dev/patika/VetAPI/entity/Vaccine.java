package dev.patika.VetAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "vaccine")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "protection_start_date", nullable = false)
    private LocalDate protectionStartDate;
    @Column(name = "protection_end_date", nullable = false)
    private LocalDate protectionEndDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
