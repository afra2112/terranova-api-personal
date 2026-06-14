package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.draft.patch;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.group.FarmGroup;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record PatchFarmRequest(
        ProductTypeEnum productType,
        String name,
        @Positive
        BigDecimal price,
        String description,
        Double latitude,
        Double longitude,

        @Positive(groups = FarmGroup.class)
        Double totalSpaceInM2,
        @Positive(groups = FarmGroup.class)
        Double builtSpaceInM2,
        @Positive(groups = FarmGroup.class)
        Integer stratum,
        @Positive(groups = FarmGroup.class)
        Integer roomsQuantity,
        @Positive(groups = FarmGroup.class)
        Integer bathroomsQuantity
) implements DraftPatchRequest {}