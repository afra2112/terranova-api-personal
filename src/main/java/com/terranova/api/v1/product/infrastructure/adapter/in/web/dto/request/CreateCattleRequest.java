package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request;

import com.terranova.api.v1.product.domain.model.group.CattleGroup;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.CattleGenderEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.CattleTypeEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.ProductTypeEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateCattleRequest(
        ProductTypeEnum productType,
        String name,
        BigDecimal price,
        String description,
        StatusEnum status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
        UUID idSeller,


        @NotBlank(groups = CattleGroup.class)
        String race,

        @NotNull(groups = CattleGroup.class)
        Double weightInKg,

        @NotNull(groups = CattleGroup.class)
        Double cattleAgeInYears,

        @NotNull(groups = CattleGroup.class)
        CattleGenderEnum gender,

        @NotNull(groups = CattleGroup.class)
        CattleTypeEnum cattleType,

        @NotNull(groups = CattleGroup.class)
        int quantity
) implements CreateProductRequest {}