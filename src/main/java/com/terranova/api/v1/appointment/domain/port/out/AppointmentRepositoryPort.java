package com.terranova.api.v1.appointment.domain.port.out;

import com.terranova.api.v1.appointment.domain.model.Appointment;

public interface AppointmentRepositoryPort {

    Appointment save(Appointment appointment);
}
