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
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH a.animal WHERE " +
            "a.doctor.id IN :doctorIds " +
            "AND a.appointmentDate BETWEEN :startDate AND :endDate")
    List<Appointment> findAppointmentsByDateRangeAndDoctors(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("doctorIds") List<Long> doctorIds);

    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH a.animal WHERE " +
            "a.appointmentDate BETWEEN :startDate AND :endDate " +
            "AND a.animal.id IN :animalIds")
    List<Appointment> findAppointmentsByDateRangeAndAnimals(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("animalIds") List<Long> animalIds);
}
