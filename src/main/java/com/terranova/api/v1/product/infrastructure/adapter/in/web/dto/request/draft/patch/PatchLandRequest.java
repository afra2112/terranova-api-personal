package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.draft.patch;

import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.group.LandGroup;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record PatchLandRequest(
        ProductTypeEnum productType,
        String name,
        @Positive
        BigDecimal price,
        String description,
        Double latitude,
        Double longitude,

        @Positive(groups = LandGroup.class)
        Double landSizeInM2,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access,
        String currentServices
) implements DraftPatchRequest { }