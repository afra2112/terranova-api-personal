package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.jpa;

import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {
}
