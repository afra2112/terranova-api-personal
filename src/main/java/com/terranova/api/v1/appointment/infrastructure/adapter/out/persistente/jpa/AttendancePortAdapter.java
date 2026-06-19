package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.jpa;

import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;
import com.terranova.api.v1.appointment.domain.port.out.AttendanceRepositoryPort;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.AttendanceMapperPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AttendancePortAdapter implements AttendanceRepositoryPort {

    private final AttendanceJpaRepository attendanceJpaRepository;
    private final AttendanceMapperPersistence attendanceMapperPersistence;

    @Override
    public Attendance save(Attendance attendance) {
        return attendanceMapperPersistence.entityToDomain(attendanceJpaRepository.save(attendanceMapperPersistence.domainToEntity(attendance)));
    }

    @Override
    public long countEnrolledReservations(Long appointmentId) {
        return attendanceJpaRepository.countByStatusAndAppointment_AppointmentId(AttendanceStatusEnum.ENROLLED, appointmentId);
    }

    @Override
    public boolean existsByAppointmentAndBuyer(Long appointmentId, UUID buyerId) {
        return attendanceJpaRepository.existsByUserIdAndAppointment_AppointmentId(buyerId, appointmentId);
    }
}
