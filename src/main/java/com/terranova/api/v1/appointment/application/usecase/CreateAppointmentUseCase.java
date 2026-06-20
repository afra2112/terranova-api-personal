package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.command.CreateAppointmentCommand;
import com.terranova.api.v1.appointment.domain.model.product.enums.StatusEnum;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.ProductServicePort;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.MapperIn;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.product.dto.ProductMetadataResponse;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class CreateAppointmentUseCase {

    public final AppointmentRepositoryPort appointmentRepositoryPort;
    public final ProductServicePort productServicePort;
    public final MapperIn mapperIn;
    public final AuthFacade authFacade;

    public CreateAppointmentUseCase(AppointmentRepositoryPort appointmentRepositoryPort, MapperIn mapperIn, ProductServicePort productServicePort, AuthFacade authFacade) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
        this.mapperIn = mapperIn;
        this.productServicePort = productServicePort;
        this.authFacade = authFacade;
    }

    public Appointment createAppointment(CreateAppointmentCommand command){

        validateDates(command);

        ProductMetadataResponse product = productServicePort.getProductMetadataById(List.of(command.productId())).getFirst();

        validateOwnership(product);

        validateProductStatus(product);

        validateOverlappingAppointments(command);

        validateQuota(command.productId());

        return appointmentRepositoryPort.save(
                mapperIn.commandToDomain(command)
        );
    }

    private void validateDates(CreateAppointmentCommand command){

        if(command.endTime().isBefore(command.startTime())){
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_TIME,
                    "End time must be after start time"
            );
        }

        if((command.date().isEqual(LocalDate.now()) || command.date().isBefore(LocalDate.now())) && command.startTime().isBefore(LocalTime.now())){
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_TIME,
                    "Appointment must be scheduled in the future"
            );
        }
    }

    private void validateOwnership(ProductMetadataResponse product){

        UUID authenticatedUser = authFacade.getAuthenticatedId();

        if(!product.sellerId().equals(authenticatedUser)){
            throw new BusinessException(
                    ErrorCodeEnum.UNAUTHORIZED,
                    "Only the product owner can create appointments"
            );
        }
    }

    private void validateProductStatus(ProductMetadataResponse product){

        if(product.status() != StatusEnum.PUBLISHED){
            throw new BusinessException(
                    ErrorCodeEnum.WRONG_PRODUCT_STATUS,
                    "Appointments can only be created for published products"
            );
        }
    }

    private void validateOverlappingAppointments(CreateAppointmentCommand command){

        boolean overlap = appointmentRepositoryPort.existsOverlappingAppointment(
                        command.productId(),
                        command.startTime(),
                        command.endTime(),
                        command.date()
                );

        if(overlap){
            throw new BusinessException(
                    ErrorCodeEnum.APPOINTMENT_OVERLAP,
                    "Appointment overlaps with an existing appointment"
            );
        }
    }

    private void validateQuota(Long productId){

        int currentAppointments =
                appointmentRepositoryPort.countFutureAppointments(
                        productId
                );

        final int MAX_APPOINTMENTS = 50;

        if(currentAppointments >= MAX_APPOINTMENTS){
            throw new BusinessException(
                    ErrorCodeEnum.APPOINTMENT_QUOTA_EXCEEDED
            );
        }
    }
}
