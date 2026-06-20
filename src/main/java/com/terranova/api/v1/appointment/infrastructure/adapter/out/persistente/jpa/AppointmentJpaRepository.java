package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.jpa;

import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {

    AppointmentEntity findByAppointmentId(Long appointmentId);

    @Query("SELECT a FROM AppointmentEntity a WHERE a.appointmentId IN :ids")
    List<AppointmentEntity> getAllByIds(List<Long> ids);

    @Query("SELECT a FROM AppointmentEntity a WHERE a.productId IN :ids")
    List<AppointmentEntity> getByProductsIds(@Param("ids") List<Long> ids);

    @Query("SELECT COUNT(a) > 0 FROM AppointmentEntity a WHERE a.productId = :productId AND a.startTime < :endTime AND a.endTime > :startTime")
    boolean existsOverlappingAppointment(
            @Param("productId") Long productId,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    @Query("SELECT COUNT(a) FROM AppointmentEntity a WHERE a.productId = :productId AND a.endTime >= CURRENT_TIMESTAMP")
    int countFutureAppointments(
            @Param("productId") Long productId
    );
}
