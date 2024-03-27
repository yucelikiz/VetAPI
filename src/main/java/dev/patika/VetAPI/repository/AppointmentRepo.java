package dev.patika.VetAPI.repository;

import dev.patika.VetAPI.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    @Query("SELECT a FROM Appointment a WHERE " +
            "a.doctor.id = :doctorId " +
            "AND a.appointmentDate = :appointmentDate " +
            "AND ((a.appointmentTimeStart >= :startTime AND a.appointmentTimeStart < :endTime) " +
            "OR (a.appointmentTimeEnd > :startTime AND a.appointmentTimeEnd <= :endTime) " +
            "OR (:startTime >= a.appointmentTimeStart AND :startTime < a.appointmentTimeEnd))")
    List<Appointment> findOverlappingAppointments(
            @Param("doctorId") Long doctorId,
            @Param("appointmentDate") LocalDate appointmentDate,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime
    );

    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH a.animal WHERE " +
            "LOWER(a.doctor.name) LIKE LOWER(CONCAT('%', :doctorName, '%')) " +
            "AND a.appointmentDate BETWEEN :startDate AND :endDate")
    List<Appointment> findAppointmentsByDateRangeAndDoctors(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("doctorName") String doctorName);

    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH a.animal WHERE " +
            "a.appointmentDate BETWEEN :startDate AND :endDate " +
            "AND LOWER(a.animal.name) LIKE LOWER(CONCAT('%', :animalName, '%'))")
    List<Appointment> findAppointmentsByDateRangeAndAnimals(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("animalName") String animalName);
}
