package dev.patika.VetAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.patika.VetAPI.entity.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
    Optional<Customer> findByName(String name);
}
