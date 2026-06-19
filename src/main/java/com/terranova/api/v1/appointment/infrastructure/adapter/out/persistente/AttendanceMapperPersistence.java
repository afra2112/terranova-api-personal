package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente;

import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity.AttendanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AttendanceMapperPersistence {

    @Mapping(target = "appointmentId", source = "appointment.appointmentId")
    Attendance entityToDomain(AttendanceEntity entity);

    @Mapping(target = "appointment.appointmentId", source = "appointmentId")
    AttendanceEntity domainToEntity(Attendance domain);
}
