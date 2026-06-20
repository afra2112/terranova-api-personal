package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.AttendanceRepositoryPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
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
                        attendance.attended()
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
}
