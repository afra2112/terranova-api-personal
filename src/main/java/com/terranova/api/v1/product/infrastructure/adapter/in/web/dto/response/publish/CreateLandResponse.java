package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.publish;

import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.appointment.AppointmentResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.image.ImageResponse;
import com.terranova.api.v1.product.domain.model.SellerSummary;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record CreateLandResponse(
        Long productId,
        ProductTypeEnum productType,
        String name,
        BigDecimal price,
        String description,
        StatusEnum status,
        LocalDate publishDate,
        String city,
        String department,
        String country,
        Double latitude,
        Double longitude,
        List<ImageResponse> images,
        List<AppointmentResponse> appointments,
        SellerSummary sellerSummary,

        Double landSizeInM2,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access,
        String currentServices
) implements CreateProductResponse { }