package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.appointment;

import com.terranova.api.v1.product.domain.model.enums.StatusEnum;

import java.util.UUID;

public record ProductAppointmentInfo(
        Long productId,
        UUID sellerId,
        StatusEnum status
) {}