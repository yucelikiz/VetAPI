package dev.patika.VetAPI.repository;

import dev.patika.VetAPI.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
    List<Report> findAll();

    Optional<Report> findByTitle(String title);
}
