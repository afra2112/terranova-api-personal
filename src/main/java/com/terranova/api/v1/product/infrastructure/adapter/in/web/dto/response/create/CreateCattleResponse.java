package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.create;

import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.AppointmentResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.ImageResponse;
import com.terranova.api.v1.product.domain.model.SellerSummary;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record CreateCattleResponse(
        Long productId,
        ProductTypeEnum productType,
        String name,
        BigDecimal price,
        String description,
        StatusEnum status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
        List<ImageResponse> images,
        List<AppointmentResponse> appointments,
        SellerSummary sellerSummary,

        String race,
        Double weightInKg,
        Double cattleAgeInYears,
        CattleGenderEnum gender,
        CattleTypeEnum cattleType,
        int quantity
) implements CreateProductResponse {}