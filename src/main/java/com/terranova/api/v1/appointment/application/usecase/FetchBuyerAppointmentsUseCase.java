package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.AttendanceRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.ProductServicePort;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.MyAppointmentsResponse;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.product.dto.ProductMetadataResponse;
import com.terranova.api.v1.shared.security.utils.AuthFacade;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FetchBuyerAppointmentsUseCase {

    private final AuthFacade authFacade;
    private final ProductServicePort productServicePort;
    private final AttendanceRepositoryPort attendanceRepositoryPort;
    private final AppointmentRepositoryPort appointmentRepositoryPort;

    public FetchBuyerAppointmentsUseCase(AuthFacade authFacade, ProductServicePort productServicePort, AttendanceRepositoryPort attendanceRepositoryPort, AppointmentRepositoryPort appointmentRepositoryPort) {
        this.authFacade = authFacade;
        this.productServicePort = productServicePort;
        this.attendanceRepositoryPort = attendanceRepositoryPort;
        this.appointmentRepositoryPort = appointmentRepositoryPort;
    }

    public List<MyAppointmentsResponse> getMyAppointments(){

        UUID buyerId = authFacade.getAuthenticatedId();

        List<Attendance> reservations = attendanceRepositoryPort.findByUserId(buyerId);

        if(reservations.isEmpty()){
            return List.of();
        }

        List<Long> appointmentIds = reservations.stream()
                        .map(Attendance::appointmentId)
                        .toList();

        Map<Long, Appointment> appointments = appointmentRepositoryPort
                        .getAppointmentsByIds(appointmentIds)
                        .stream()
                        .collect(Collectors.toMap(
                                Appointment::appointmentId,
                                Function.identity()
                        ));

        List<Long> productIds = appointments.values()
                        .stream()
                        .map(Appointment::productId)
                        .distinct()
                        .toList();

        Map<Long, ProductMetadataResponse> products = productServicePort.getProductMetadataById(productIds)
                .stream()
                .collect(Collectors.toMap(
                        ProductMetadataResponse::productId,
                        Function.identity()
                ));

        return reservations.stream().map(reservation -> {

                    Appointment appointment = appointments.get(reservation.appointmentId());

                    ProductMetadataResponse product = products.get(appointment.productId());

                    return new MyAppointmentsResponse(
                            reservation.attendanceId(),
                            product.productId(),
                            product.productName(),
                            product.productDescription(),
                            product.price(),
                            product.latitude(),
                            product.longitude(),
                            appointment.date(),
                            appointment.startTime(),
                            appointment.endTime(),
                            reservation.status()
                    );
                })
                .sorted(Comparator.comparing(MyAppointmentsResponse::date)
                        .thenComparing(MyAppointmentsResponse::startTime))
                .toList();
    }
}
