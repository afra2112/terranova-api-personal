package com.terranova.api.v1.product.domain.model.appointment;

import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record Attendance(
        Long attendanceId,
        Long appointmentId,
        UUID userId,
        AttendanceStatusEnum status,
        LocalDateTime inscriptionDate
) {
}