package com.terranova.api.v1.product.domain.model.command.draft;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;

import java.util.UUID;

public record CreateDraftCommand(
        Long productId,
        StatusEnum status,
        ProductTypeEnum productType,
        UUID sellerId
) {
}
