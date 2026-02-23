package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response;

import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
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
        UUID sellerId,

        Double landSizeInM2,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access,
        String currentServices
) implements CreateProductResponse { }