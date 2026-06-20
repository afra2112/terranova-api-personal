package com.terranova.api.v1.product.infrastructure.adapter.out.appointment;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.appointment.AppointmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "appointment", url = "http://localhost:8080/api/v1/appointments")
public interface AppointmentFeign {

    @GetMapping("/products/{ids}")
    Map<Long, List<AppointmentResponse>> getAppointmentsByProductId(@PathVariable List<Long> ids);

    @PostMapping("/internal/products/{productId}/cancel-future")
    List<Appointment> cancelFutureAppointments(@PathVariable Long productId);
}
