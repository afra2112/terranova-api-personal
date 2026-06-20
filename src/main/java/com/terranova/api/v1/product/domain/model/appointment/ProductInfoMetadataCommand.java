package com.terranova.api.v1.product.domain.model.appointment;


import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductInfoMetadataCommand(
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
