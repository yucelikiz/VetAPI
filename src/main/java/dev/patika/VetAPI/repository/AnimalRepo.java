package dev.patika.VetAPI.repository;

import dev.patika.VetAPI.dto.request.AnimalRequest;
import dev.patika.VetAPI.dto.response.AnimalResponse;
import dev.patika.VetAPI.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    Optional<Animal> findByName(String name);

    @Query("SELECT a FROM Animal a JOIN a.customer c WHERE c.id = :customerId")
    List<Animal> findByCustomerId(@Param("customerId") Long customerId);

}
