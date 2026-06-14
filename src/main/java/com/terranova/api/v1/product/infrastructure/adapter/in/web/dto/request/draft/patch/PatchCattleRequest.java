package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.draft.patch;

import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.group.CattleGroup;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record PatchCattleRequest(
        ProductTypeEnum productType,
        String name,
        @Positive
        BigDecimal price,
        String description,
        Double latitude,
        Double longitude,

        String race,
        @Positive(groups = CattleGroup.class)
        Double weightInKg,
        @Positive(groups = CattleGroup.class)
        Double cattleAgeInYears,
        CattleGenderEnum gender,
        CattleTypeEnum cattleType,
        @Positive(groups = CattleGroup.class)
        Integer quantity
) implements DraftPatchRequest {}
