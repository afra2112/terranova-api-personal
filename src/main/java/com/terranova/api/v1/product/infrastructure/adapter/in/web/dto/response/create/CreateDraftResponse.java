package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.create;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;

import java.util.UUID;

public record CreateDraftResponse(
        Long productId,
        StatusEnum status,
        ProductTypeEnum productType,
        UUID sellerId
) {
}
