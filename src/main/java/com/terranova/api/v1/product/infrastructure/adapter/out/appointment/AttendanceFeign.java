package com.terranova.api.v1.product.infrastructure.adapter.out.appointment;

import com.terranova.api.v1.product.domain.model.appointment.Attendance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "attendance", url = "http://localhost:8080/api/v1/appointments/attendances")
public interface AttendanceFeign {

    @PostMapping("/batch")
    List<Attendance> getByIds(@RequestBody List<Long> ids);

    @PostMapping("/internal/cancel")
    void cancelAttendancesByAppointmentsIds(@RequestBody List<Long> appointmentsIds);
}
