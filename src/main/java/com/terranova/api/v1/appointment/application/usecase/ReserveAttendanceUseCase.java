package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;
import com.terranova.api.v1.appointment.domain.model.product.ProductResponse;
import com.terranova.api.v1.appointment.domain.model.product.enums.StatusEnum;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.AttendanceRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.ProductServicePort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReserveAttendanceUseCase {

    private final AppointmentRepositoryPort appointmentRepositoryPort;
    private final AttendanceRepositoryPort reservationRepositoryPort;
    private final ProductServicePort productServicePort;
    private final AuthFacade authFacade;

    public ReserveAttendanceUseCase(AppointmentRepositoryPort appointmentRepositoryPort, AttendanceRepositoryPort reservationRepositoryPort, ProductServicePort productServicePort, AuthFacade authFacade) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
        this.reservationRepositoryPort = reservationRepositoryPort;
        this.productServicePort = productServicePort;
        this.authFacade = authFacade;
    }

    public Attendance reserve(Long appointmentId) {

        Appointment appointment = appointmentRepositoryPort.findById(appointmentId).orElseThrow(
                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Appointment not found by id: " + appointmentId)
        );

        validateFutureAppointment(appointment);

        ProductResponse product = productServicePort.getProductById(appointment.productId()).orElseThrow(
                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND)
        );

        validatePublishedProduct(product);

        UUID buyerId = authFacade.getAuthenticatedId();

        validateDuplicateReservation(appointmentId, buyerId);

        validateQuota(appointment);

        return reservationRepositoryPort.save(
                new Attendance(
                        null,
                        appointmentId,
                        buyerId,
                        AttendanceStatusEnum.ENROLLED,
                        LocalDateTime.now(),
                        false
                )
        );
    }

    private void validateFutureAppointment(Appointment appointment){

        LocalDateTime appointmentDateTime = LocalDateTime.of(appointment.date(), appointment.startTime());

        if(appointmentDateTime.isBefore(LocalDateTime.now())){
            throw new BusinessException(
                    ErrorCodeEnum.APPOINTMENT_EXPIRED, "You're trying to assist to an appointment before the present. Appointment date: " + appointmentDateTime
            );
        }
    }

    private void validatePublishedProduct(ProductResponse product){
        if(product.status() != StatusEnum.PUBLISHED){
            throw new BusinessException(
                    ErrorCodeEnum.WRONG_PRODUCT_STATUS,
                    "Product is not published"
            );
        }
    }

    private void validateDuplicateReservation(Long appointmentId, UUID buyerId){
        if(reservationRepositoryPort.existsByAppointmentAndBuyer(appointmentId, buyerId)){
            throw new BusinessException(
                    ErrorCodeEnum.APPOINTMENT_ALREADY_RESERVED, "You've already enrolled to this appointment, appointment id: " + appointmentId
            );
        }
    }

    private void validateQuota(Appointment appointment){

        long reservations = reservationRepositoryPort.countEnrolledReservations(appointment.appointmentId());

        if(reservations >= appointment.maximumQuorum()){
            throw new BusinessException(
                    ErrorCodeEnum.APPOINTMENT_FULL,
                    "This appointment party has reached it's party size and it's already full. Maximum quorum: " + appointment.maximumQuorum()
            );
        }
    }
}
