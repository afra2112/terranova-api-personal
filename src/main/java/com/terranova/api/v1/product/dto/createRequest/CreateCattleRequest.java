package com.terranova.api.v1.product.dto.createRequest;

import com.terranova.api.v1.product.dto.CattleGroup;
import com.terranova.api.v1.product.enums.CattleGenderEnum;
import com.terranova.api.v1.product.enums.CattleTypeEnum;
import com.terranova.api.v1.product.enums.ProductType;
import com.terranova.api.v1.product.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateCattleRequest(
        ProductType productType,
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
