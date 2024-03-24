package dev.patika.VetAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dev.patika.VetAPI.entity.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
    Optional<Customer> findByName(String name);

    @Query("SELECT c FROM Customer c WHERE lower(name) LIKE lower(concat( :name, '%'))")
    List<Customer> findByNameIgnoreCase(String name);


    Optional<Customer> findByMail(String mail);
}
