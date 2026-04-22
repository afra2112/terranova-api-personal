package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;

import java.util.List;

public class GetAppointmentsByProductUseCase {

    private final AppointmentRepositoryPort appointmentRepositoryPort;

    public GetAppointmentsByProductUseCase(AppointmentRepositoryPort appointmentRepositoryPort) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
    }

    public List<Appointment> getAppointmentsByProduct(Long productId){
        return appointmentRepositoryPort.getByProductId(productId);
    }
}
