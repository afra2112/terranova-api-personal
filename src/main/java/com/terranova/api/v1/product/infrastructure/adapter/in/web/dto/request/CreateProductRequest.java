package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.ProductTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "productType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateCattleRequest.class, name = "CATTLE"),
        @JsonSubTypes.Type(value = CreateFarmRequest.class, name = "FARM"),
        @JsonSubTypes.Type(value = CreateLandRequest.class, name = "LAND")
})
public sealed interface CreateProductRequest permits CreateCattleRequest, CreateFarmRequest, CreateLandRequest {
    @NotNull
    ProductTypeEnum productType();
    @NotBlank
    String name();
    @NotNull
    @Positive
    BigDecimal price();
    @NotBlank
    String description();
    @NotBlank
    String city();
    @NotNull
    Double latitude();
    @NotNull
    Double longitude();
    @NotNull
    UUID idSeller();
}