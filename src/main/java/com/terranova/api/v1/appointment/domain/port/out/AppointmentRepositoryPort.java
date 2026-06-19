package com.terranova.api.v1.appointment.domain.port.out;

import com.terranova.api.v1.appointment.domain.model.Appointment;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AppointmentRepositoryPort {

    Appointment save(Appointment appointment);

    Optional<Appointment> findById(Long id);

    Map<Long, List<Appointment>> getByProductsIds(List<Long> productsIds);

    boolean existsOverlappingAppointment(
            Long productId,
            LocalTime startTime,
            LocalTime endTime
    );

    int countFutureAppointments(Long productId);
}
