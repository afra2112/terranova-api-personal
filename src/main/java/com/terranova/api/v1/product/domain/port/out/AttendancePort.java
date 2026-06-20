package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.appointment.Attendance;

public interface AttendancePort {

    Attendance getAttendanceById(Long id);
}
