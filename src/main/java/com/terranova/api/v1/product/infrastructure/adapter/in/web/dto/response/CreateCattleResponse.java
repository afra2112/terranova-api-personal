package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response;

import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.CattleGenderEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.CattleTypeEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.ProductTypeEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.StatusEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateCattleResponse(
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

        String race,
        Double weightInKg,
        Double cattleAgeInYears,
        CattleGenderEnum gender,
        CattleTypeEnum cattleType,
        int quantity
) implements CreateProductResponse {}