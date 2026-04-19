package com.terranova.api.v1.appointment.infrastructure.adapter.in.web;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.command.CreateAppointmentCommand;
import com.terranova.api.v1.appointment.domain.model.enums.AppointmentStatusEnum;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.request.CreateAppointmentRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MapperIn {

    public CreateAppointmentCommand requestToCommand(CreateAppointmentRequest request){
        return new CreateAppointmentCommand(
                request.date(),
                request.startTime(),
                request.endTime(),
                request.description(),
                request.productId(),
                request.maxQuorum()
        );
    }

    public Appointment commandToDomain(CreateAppointmentCommand command){
        return new Appointment(
                null,
                AppointmentStatusEnum.PROGRAMMED,
                command.maxQuorum(),
                List.of(),
                false,
                0,
                command.maxQuorum(),
                command.date(),
                command.startTime(),
                command.endTime(),
                command.description(),
                command.productId(),
                null,
                null,
                LocalDateTime.now(),
                0
        );
    }
}
