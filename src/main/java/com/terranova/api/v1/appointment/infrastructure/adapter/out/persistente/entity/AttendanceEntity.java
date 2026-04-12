package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity;

import com.terranova.api.v1.appointment.domain.model.enums.AttendanceStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendances")
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @Column(nullable = false)
    UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AttendanceStatusEnum status;

    @Column(nullable = false)
    LocalDateTime inscriptionDate;

    @Column(nullable = false)
    boolean attended;

    @ManyToOne
    @JoinColumn(name = "appointmentId")
    AppointmentEntity appointment;
}
