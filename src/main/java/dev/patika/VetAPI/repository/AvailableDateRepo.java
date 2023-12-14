package dev.patika.VetAPI.repository;

import dev.patika.VetAPI.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {
 AvailableDate findByAvailableDateAndDoctorId(LocalDate availableDate, Long doctorId);
}
