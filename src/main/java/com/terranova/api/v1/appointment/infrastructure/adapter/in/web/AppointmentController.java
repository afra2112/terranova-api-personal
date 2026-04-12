package com.terranova.api.v1.appointment.infrastructure.adapter.in.web;

import com.terranova.api.v1.appointment.application.usecase.CreateAppointmentUseCase;
import com.terranova.api.v1.appointment.domain.model.command.CreateAppointmentCommand;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.request.CreateAppointmentRequest;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.CreateAppointmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    public final CreateAppointmentUseCase createAppointmentUseCase;
    public final MapperOut mapperOut;
    public final MapperIn mapperIn;

    @PostMapping
    public CreateAppointmentResponse createAppointment(@RequestBody @Valid CreateAppointmentRequest request){
        return mapperOut.domainToResponse(createAppointmentUseCase.createAppointment(mapperIn.requestToCommand(request)));
    }
}
