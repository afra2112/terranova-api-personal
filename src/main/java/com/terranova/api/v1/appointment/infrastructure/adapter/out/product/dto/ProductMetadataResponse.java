package com.terranova.api.v1.appointment.infrastructure.adapter.out.product.dto;

import com.terranova.api.v1.appointment.domain.model.product.enums.StatusEnum;

import java.util.UUID;

public record ProductMetadataResponse(
        Long productId,
        UUID sellerId,
        StatusEnum status
) {
}
