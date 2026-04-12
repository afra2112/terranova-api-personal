package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity;

import com.terranova.api.v1.appointment.domain.model.enums.AppointmentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum status;

    @Column(nullable = false)
    private int maxQuorum;

    @Column(nullable = false)
    private boolean deleted = false;

    @Column(nullable = false)
    private int takenSlots;

    @Column(nullable = false)
    private int availableSlots;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = true)
    private LocalDateTime latestReprogramming;

    @Column(nullable = true)
    private LocalDateTime latestBlockedReprogramming;

    @Column(nullable = true)
    private LocalDateTime newAvailableReprogrammingDate;

    @Column(nullable = false)
    private int reprogrammingAttempts;

    @OneToMany(mappedBy = "appointment")
    List<AttendanceEntity> attendances;
}
