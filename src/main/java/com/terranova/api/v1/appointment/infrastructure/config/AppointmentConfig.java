package com.terranova.api.v1.appointment.infrastructure.config;

import com.terranova.api.v1.appointment.application.usecase.*;
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

    @Bean
    public CancelAppointmentUseCase cancelAppointmentUseCase(AppointmentRepositoryPort appointmentRepositoryPort, AttendanceRepositoryPort attendanceRepositoryPort, AuthFacade authFacade){
        return new CancelAppointmentUseCase(attendanceRepositoryPort, appointmentRepositoryPort, authFacade);
    }

    @Bean
    public FetchBuyerAppointmentsUseCase fetchBuyerAppointmentsUseCase(ProductServicePort productServicePort, AppointmentRepositoryPort appointmentRepositoryPort, AttendanceRepositoryPort attendanceRepositoryPort, AuthFacade authFacade){
        return new FetchBuyerAppointmentsUseCase(authFacade, productServicePort, attendanceRepositoryPort, appointmentRepositoryPort);
    }

    @Bean
    public BatchAttendancesByIdsUseCase batchAttendancesByIdsUseCase(AttendanceRepositoryPort attendanceRepositoryPort){
        return new BatchAttendancesByIdsUseCase(attendanceRepositoryPort);
    }
}
