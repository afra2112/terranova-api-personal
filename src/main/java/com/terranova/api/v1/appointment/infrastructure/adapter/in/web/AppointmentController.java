package com.terranova.api.v1.appointment.infrastructure.adapter.in.web;

import com.terranova.api.v1.appointment.application.usecase.*;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.request.CreateAppointmentRequest;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.AppointmentResponse;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.AttendanceResponse;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.MyAppointmentsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final GetAppointmentsByProductUseCase getAppointmentsByProductUseCase;
    private final FetchBuyerAppointmentsUseCase fetchBuyerAppointmentsUseCase;
    private final CancelAppointmentUseCase cancelAppointmentUseCase;
    private final ReserveAttendanceUseCase reserveAttendanceUseCase;
    private final AttendanceMapperOut attendanceMapperOut;
    private final MapperOut mapperOut;
    private final MapperIn mapperIn;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody @Valid CreateAppointmentRequest request){
        return ResponseEntity.ok(mapperOut.domainToResponse(createAppointmentUseCase.createAppointment(mapperIn.requestToCommand(request))));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<List<MyAppointmentsResponse>> getBuyerAppointments(){
        return ResponseEntity.ok(fetchBuyerAppointmentsUseCase.getMyAppointments());
    }

    @PostMapping("/{id}/attendances")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<AttendanceResponse> reserveAnAppointment(@PathVariable Long id){
        return ResponseEntity.ok(attendanceMapperOut.domainToResponse(reserveAttendanceUseCase.reserve(id)));
    }

    @DeleteMapping("/attendances/{id}")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<Void> cancelAttendance(@PathVariable Long id){
        cancelAppointmentUseCase.cancel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/{ids}")
    public Map<Long, List<AppointmentResponse>> getAllAppointmentsByProduct(@PathVariable List<Long> ids){
        return getAppointmentsByProductUseCase.getAppointmentsByProducts(ids)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .map(mapperOut::domainToResponse)
                                .toList()
                ));
    }
}
