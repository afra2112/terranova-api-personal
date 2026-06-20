package com.terranova.api.v1.appointment.domain.port.out;

import com.terranova.api.v1.appointment.domain.model.Attendance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendanceRepositoryPort {

    Optional<Attendance> findById(Long id);

    List<Attendance> findByUserId(UUID id);

    Attendance save(Attendance reservation);

    long countEnrolledReservations(Long appointmentId);

    boolean existsByAppointmentAndBuyer(Long appointmentId, UUID buyerId);

    boolean existsActiveAttendanceByUserAndProduct(UUID userId, Long productId);
}
