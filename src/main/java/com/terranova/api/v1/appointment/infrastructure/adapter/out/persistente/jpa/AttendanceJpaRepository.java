package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.jpa;

import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttendanceJpaRepository extends JpaRepository<AttendanceEntity, Long> {

    AttendanceEntity findByAttendanceId(Long id);

    List<AttendanceEntity> findByUserId(UUID userId);

    long countByStatusAndAppointment_AppointmentId(AttendanceStatusEnum status, Long appointmentAppointmentId);

    boolean existsByUserIdAndAppointment_AppointmentId(UUID userId, Long appointmentId);
}
