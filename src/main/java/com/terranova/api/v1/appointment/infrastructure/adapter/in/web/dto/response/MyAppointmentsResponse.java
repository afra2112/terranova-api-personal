package com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response;

import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record MyAppointmentsResponse(
        Long attendanceId,
        Long productId,
        String productName,
        String description,
        BigDecimal price,
        Double latitude,
        Double longitude,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        AttendanceStatusEnum status
) {
}
