package com.terranova.api.v1.appointment.infrastructure.adapter.in.web;

import com.terranova.api.v1.appointment.application.usecase.CreateAppointmentUseCase;
import com.terranova.api.v1.appointment.application.usecase.GetAppointmentsByProductUseCase;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.request.CreateAppointmentRequest;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.AppointmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    public final CreateAppointmentUseCase createAppointmentUseCase;
    public final GetAppointmentsByProductUseCase getAppointmentsByProductUseCase;
    public final MapperOut mapperOut;
    public final MapperIn mapperIn;

    @PostMapping
    public AppointmentResponse createAppointment(@RequestBody @Valid CreateAppointmentRequest request){
        return mapperOut.domainToResponse(createAppointmentUseCase.createAppointment(mapperIn.requestToCommand(request)));
    }

    @GetMapping("/product/{id}")
    public List<AppointmentResponse> getAllAppointmentsByProduct(@PathVariable Long id){
        return getAppointmentsByProductUseCase.getAppointmentsByProduct(id)
                .stream()
                .map(mapperOut::domainToResponse)
                .toList();
    }
}
