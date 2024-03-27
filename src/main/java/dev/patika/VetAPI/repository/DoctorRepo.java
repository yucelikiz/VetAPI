package dev.patika.VetAPI.repository;

import dev.patika.VetAPI.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE lower(name) LIKE lower(concat('%', :name, '%'))")
    List<Doctor> findByName(String name);

    Optional<Doctor> findByNameAndMail(String name, String mail);

    Optional<Doctor> findAvailableDatesById(Long doctorId);
}
