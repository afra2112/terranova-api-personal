package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response;

import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.LandAccessEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.LandTopographyEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.ProductTypeEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.StatusEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateLandResponse(
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

        Double landSizeInM2,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access,
        String currentServices
) implements CreateProductResponse { }