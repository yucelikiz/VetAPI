package dev.patika.VetAPI.repository;

import dev.patika.VetAPI.entity.Animal;
import dev.patika.VetAPI.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine,Long> {
    Vaccine findByCode(String code);
    Optional<Vaccine> findByNameAndCodeAndAnimal_Id(String name, String code, Long animalId);
    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByName(String name);

    @Query("SELECT v.animal FROM Vaccine v WHERE v.protectionEndDate BETWEEN :startDate AND :endDate")
    List<Animal> findAnimalsByVaccineProtectionEndDateBetween(
            @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
