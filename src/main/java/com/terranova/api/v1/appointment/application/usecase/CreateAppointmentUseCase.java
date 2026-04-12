package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.command.CreateAppointmentCommand;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.MapperIn;

public class CreateAppointmentUseCase {

    public final AppointmentRepositoryPort appointmentRepositoryPort;
    public final MapperIn mapperIn;

    public CreateAppointmentUseCase(AppointmentRepositoryPort appointmentRepositoryPort, MapperIn mapperIn) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
        this.mapperIn = mapperIn;
    }

    public Appointment createAppointment(CreateAppointmentCommand command){
        return appointmentRepositoryPort.save(mapperIn.commandToDomain(command));
    }
}
