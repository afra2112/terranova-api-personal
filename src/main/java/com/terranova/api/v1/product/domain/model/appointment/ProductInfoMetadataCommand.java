package com.terranova.api.v1.product.domain.model.appointment;

import com.terranova.api.v1.product.domain.model.enums.StatusEnum;

import java.util.UUID;

public record ProductInfoMetadataCommand(
        Long productId,
        UUID sellerId,
        StatusEnum status
) {
}
