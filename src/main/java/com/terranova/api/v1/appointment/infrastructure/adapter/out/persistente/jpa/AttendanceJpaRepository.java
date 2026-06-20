package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.jpa;

import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AttendanceJpaRepository extends JpaRepository<AttendanceEntity, Long> {

    AttendanceEntity findByAttendanceId(Long id);

    List<AttendanceEntity> findByUserId(UUID userId);

    long countByStatusAndAppointment_AppointmentId(AttendanceStatusEnum status, Long appointmentAppointmentId);

    boolean existsByUserIdAndAppointment_AppointmentId(UUID userId, Long appointmentId);

    @Query("SELECT a FROM AttendanceEntity a WHERE a.attendanceId IN :ids")
    List<AttendanceEntity> batchAttendancesByIds(List<Long> ids);

    @Query("SELECT a FROM AttendanceEntity a WHERE a.appointment.appointmentId IN :ids")
    List<AttendanceEntity> batchAppointmentsByIds(List<Long> ids);

    @Query("SELECT count(a) > 0 FROM AttendanceEntity a JOIN a.appointment ap WHERE a.userId = :userId AND ap.productId = :productId AND a.status = 'ENROLLED'")
    boolean existsActiveAttendanceByUserAndProduct(UUID userId, Long productId);
}
