package com.terranova.api.v1.appointment.infrastructure.adapter.out.product.dto;

import com.terranova.api.v1.appointment.domain.model.product.enums.StatusEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductMetadataResponse(
        Long productId,
        UUID sellerId,
        String productName,
        String productDescription,
        BigDecimal price,
        Double latitude,
        Double longitude,
        StatusEnum status
) {
}
