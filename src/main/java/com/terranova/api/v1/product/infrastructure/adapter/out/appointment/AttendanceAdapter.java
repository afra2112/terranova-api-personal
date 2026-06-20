package com.terranova.api.v1.product.infrastructure.adapter.out.appointment;

import com.terranova.api.v1.product.domain.model.appointment.Attendance;
import com.terranova.api.v1.product.domain.port.out.AttendancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttendanceAdapter implements AttendancePort {

    private final AttendanceFeign attendanceFeign;

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceFeign.getById(id);
    }
}
