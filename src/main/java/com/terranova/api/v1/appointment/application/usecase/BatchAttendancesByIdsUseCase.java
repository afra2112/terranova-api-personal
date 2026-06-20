package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.port.out.AttendanceRepositoryPort;

import java.util.List;

public class BatchAttendancesByIdsUseCase {

    private final AttendanceRepositoryPort attendanceRepositoryPort;

    public BatchAttendancesByIdsUseCase(AttendanceRepositoryPort attendanceRepositoryPort) {
        this.attendanceRepositoryPort = attendanceRepositoryPort;
    }

    public List<Attendance> batchAttendancesByIds(List<Long> ids){
        return attendanceRepositoryPort.batchByIds(ids);
    }
}
