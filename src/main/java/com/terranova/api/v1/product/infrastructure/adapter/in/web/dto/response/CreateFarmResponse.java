package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response;

import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.ProductTypeEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateFarmResponse(
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

        Double totalSpaceInM2,
        Double builtSpaceInM2,
        int stratum,
        int roomsQuantity,
        int bathroomsQuantity
) implements CreateProductResponse {}
