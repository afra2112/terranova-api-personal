package com.terranova.api.v1.appointment.infrastructure.config;

import com.terranova.api.v1.appointment.application.usecase.CreateAppointmentUseCase;
import com.terranova.api.v1.appointment.application.usecase.GetAppointmentsByProductUseCase;
import com.terranova.api.v1.appointment.application.usecase.ReserveAttendanceUseCase;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.AttendanceRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.ProductServicePort;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.MapperIn;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConfig {

    @Bean
    public CreateAppointmentUseCase createAppointmentUseCase(AppointmentRepositoryPort appointmentRepositoryPort, MapperIn mapperIn, ProductServicePort productServicePort, AuthFacade authFacade){
       return new CreateAppointmentUseCase(appointmentRepositoryPort, mapperIn, productServicePort, authFacade);
    }

    @Bean
    public GetAppointmentsByProductUseCase getAppointmentsByProductUseCase(AppointmentRepositoryPort appointmentRepositoryPort){
        return new GetAppointmentsByProductUseCase(appointmentRepositoryPort);
    }

    @Bean
    public ReserveAttendanceUseCase reserveAttendanceUseCase(AppointmentRepositoryPort appointmentRepositoryPort, AttendanceRepositoryPort attendanceRepositoryPort, ProductServicePort productServicePort, AuthFacade authFacade){
        return new ReserveAttendanceUseCase(appointmentRepositoryPort, attendanceRepositoryPort, productServicePort, authFacade);
    }
}
