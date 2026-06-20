package com.terranova.api.v1.product.infrastructure.adapter.out.appointment;

import com.terranova.api.v1.product.domain.model.appointment.Attendance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "attendance", url = "http://localhost:8080/api/v1/appointments/attendances")
public interface AttendanceFeign {

    @GetMapping("/{id}")
    Attendance getById(@PathVariable Long id);
}
