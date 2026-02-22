package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.ProductTypeEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "productType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateCattleResponse.class, name = "CATTLE"),
        @JsonSubTypes.Type(value = CreateFarmResponse.class, name = "FARM"),
        @JsonSubTypes.Type(value = CreateLandResponse.class, name = "LAND")
})
public sealed interface CreateProductResponse permits CreateCattleResponse, CreateFarmResponse, CreateLandResponse {
    ProductTypeEnum productType();
    String name();
    BigDecimal price();
    String description();
    StatusEnum status();
    LocalDate publishDate();
    String city();
    Double latitude();
    Double longitude();
    UUID idSeller();
}