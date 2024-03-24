package dev.patika.VetAPI.repository;

import dev.patika.VetAPI.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a WHERE lower(name) LIKE lower(concat( :name, '%'))")
    List<Animal> findByName(String name);

    List<Animal> findByCustomerId(Long customerId);

    Optional<Animal> findByNameAndSpeciesAndBreed(String name, String species, String breed);

    Optional<Animal> findByCustomerIdAndNameAndSpeciesAndBreed (Long customerId, String name, String species, String breed);

    List<Animal> findByCustomerName( String customerName);

}
