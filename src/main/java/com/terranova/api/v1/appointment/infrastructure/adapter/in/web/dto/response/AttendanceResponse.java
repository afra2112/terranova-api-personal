package com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response;

import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record AttendanceResponse(
        Long attendanceId,
        Long appointmentId,
        UUID userId,
        AttendanceStatusEnum status,
        LocalDateTime inscriptionDate,
        boolean attended
) {
}
