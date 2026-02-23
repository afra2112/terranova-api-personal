package com.terranova.api.v1.product.domain.model.command;

import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateCattleCommand(
        ProductTypeEnum productType,
        String name,
        BigDecimal price,
        String description,
        String status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
        UUID idSeller,

        String race,
        Double weightInKg,
        Double cattleAgeInYears,
        CattleGenderEnum gender,
        CattleTypeEnum cattleType,
        int quantity
) implements CreateProductCommand {}