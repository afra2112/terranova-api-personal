package com.terranova.api.v1.product.dto.createRequest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.enums.*;
import java.math.BigDecimal;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "productType"
)
public sealed interface CreateProductRequest permits CreateCattleRequest, CreateFarmRequest, CreateLandRequest {
        ProductType productType();
        String name();
        BigDecimal price();
        String description();
        String city();
        Double latitude();
        Double longitude();
        UUID idSeller();
}