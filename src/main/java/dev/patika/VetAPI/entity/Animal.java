package dev.patika.VetAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "species", nullable = false)
    private String species;
    @Column(name = "breed", nullable = false)
    private String breed;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "color")
    private String color;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "animal",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "animal", fetch=FetchType.LAZY ,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccines;
}
