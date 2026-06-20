package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.model.enums.AppointmentCancellationReasonEnum;
import com.terranova.api.v1.appointment.domain.model.enums.AppointmentStatusEnum;
import com.terranova.api.v1.appointment.domain.model.enums.AttendanceCancellationReasonEnum;
import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.AttendanceRepositoryPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.shared.security.utils.AuthFacade;

import java.util.List;
import java.util.UUID;

public class CancelAppointmentUseCase {

    private final AttendanceRepositoryPort attendanceRepositoryPort;
    private final AppointmentRepositoryPort appointmentRepositoryPort;
    private final AuthFacade authFacade;

    public CancelAppointmentUseCase(AttendanceRepositoryPort attendanceRepositoryPort, AppointmentRepositoryPort appointmentRepositoryPort, AuthFacade authFacade) {
        this.attendanceRepositoryPort = attendanceRepositoryPort;
        this.appointmentRepositoryPort = appointmentRepositoryPort;
        this.authFacade = authFacade;
    }

    public void cancel(Long reservationId){

        Attendance attendance = attendanceRepositoryPort.findById(reservationId).orElseThrow(
                                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Reservation not found by id: " + reservationId)
        );

        validateOwnership(attendance);

        validateStatus(attendance);

        Appointment appointment = appointmentRepositoryPort.findById(attendance.appointmentId()).orElseThrow(
                                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND)
        );

        Attendance cancelled = new Attendance(
                        attendance.attendanceId(),
                        attendance.appointmentId(),
                        attendance.userId(),
                        AttendanceStatusEnum.CANCELLED,
                        attendance.inscriptionDate(),
                null
        );

        attendanceRepositoryPort.save(cancelled);

        Appointment updated = appointment.toBuilder()
                .takenSlots(appointment.takenSlots() - 1)
                .availableSlots(appointment.availableSlots() + 1)
                .build();

        appointmentRepositoryPort.save(updated);
    }

    private void validateOwnership(Attendance attendance){

        UUID currentUser = authFacade.getAuthenticatedId();

        if(!attendance.userId().equals(currentUser)){
            throw new BusinessException(
                    ErrorCodeEnum.UNAUTHORIZED
            );
        }
    }

    private void validateStatus(Attendance attendance){
        if(attendance.status() == AttendanceStatusEnum.CANCELLED){
            throw new BusinessException(
                    ErrorCodeEnum.ATTENDANCE_ALREADY_CANCELLED
            );
        }
    }

    public List<Appointment> cancelFutureAppointmentsByProduct(Long productId){

        List<Appointment> appointments = appointmentRepositoryPort.getFutureAppointmentsByProduct(productId);

        List<Appointment> cancelled = appointments.stream()
                        .map(a -> a.toBuilder()
                                .status(AppointmentStatusEnum.CANCELLED)
                                .cancellationReason(AppointmentCancellationReasonEnum.PRODUCT_SOLD)
                                .cancellationReasonMessage("This product is currently sold, appointments no longer available.")
                                .build())
                        .toList();

        appointmentRepositoryPort.saveAll(cancelled);

        return cancelled;
    }

    public void cancelAttendancesByAppointments(List<Long> appointmentIds, AttendanceCancellationReasonEnum reason){

        List<Attendance> attendances = attendanceRepositoryPort.batchByAppointmentsIds(appointmentIds);

        if(attendances.isEmpty()){
            return;
        }

        List<Attendance> cancelled = attendances.stream().filter(a -> a.status() != AttendanceStatusEnum.CANCELLED)
                        .map(a ->
                                new Attendance(
                                        a.attendanceId(),
                                        a.appointmentId(),
                                        a.userId(),
                                        AttendanceStatusEnum.CANCELLED,
                                        a.inscriptionDate(),
                                        reason
                                )
                        )
                        .toList();

        attendanceRepositoryPort.saveAll(cancelled);
    }
}
