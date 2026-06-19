package com.terranova.api.v1.appointment.infrastructure.adapter.in.web;

import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.AttendanceResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AttendanceMapperOut {

    AttendanceResponse domainToResponse(Attendance domain);
}
