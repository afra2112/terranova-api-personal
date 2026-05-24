package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.draft;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDraftRequest(
        @NotBlank
        @NotNull
        ProductTypeEnum productType
) {
}
