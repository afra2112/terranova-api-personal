package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.jpa;

import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;
import com.terranova.api.v1.appointment.domain.port.out.AttendanceRepositoryPort;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.AttendanceMapperPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AttendancePortAdapter implements AttendanceRepositoryPort {

    private final AttendanceJpaRepository attendanceJpaRepository;
    private final AttendanceMapperPersistence attendanceMapperPersistence;

    @Override
    public Optional<Attendance> findById(Long id) {
        return Optional.ofNullable(attendanceMapperPersistence.entityToDomain(attendanceJpaRepository.findByAttendanceId(id)));
    }

    @Override
    public List<Attendance> findByUserId(UUID id) {
        return attendanceJpaRepository.findByUserId(id).stream()
                .map(attendanceMapperPersistence::entityToDomain)
                .toList();
    }

    @Override
    public List<Attendance> batchByIds(List<Long> ids) {
        return attendanceJpaRepository.batchAttendancesByIds(ids).stream()
                .map(attendanceMapperPersistence::entityToDomain)
                .toList();
    }

    @Override
    public List<Attendance> batchByAppointmentsIds(List<Long> appointmentsIds) {
        return attendanceJpaRepository.batchAppointmentsByIds(appointmentsIds).stream()
                .map(attendanceMapperPersistence::entityToDomain)
                .toList();
    }

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

    @Override
    public boolean existsActiveAttendanceByUserAndProduct(UUID userId, Long productId) {
        return attendanceJpaRepository.existsActiveAttendanceByUserAndProduct(userId, productId);
    }

    @Override
    public void saveAll(List<Attendance> attendances) {
        attendanceJpaRepository.saveAll(attendances.stream()
                .map(attendanceMapperPersistence::domainToEntity).toList())
        ;
    }
}
