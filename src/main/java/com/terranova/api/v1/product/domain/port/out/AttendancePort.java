package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.appointment.Attendance;

import java.util.List;

public interface AttendancePort {

    List<Attendance> getAttendancesByIds(List<Long> ids);

    void cancelAttendancesByAppointments(List<Long> appointmentsIds);
}
