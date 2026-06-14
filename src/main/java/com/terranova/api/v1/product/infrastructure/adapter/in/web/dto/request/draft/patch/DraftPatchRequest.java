package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.draft.patch;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import java.math.BigDecimal;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "productType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PatchCattleRequest.class, name = "CATTLE"),
        @JsonSubTypes.Type(value = PatchFarmRequest.class, name = "FARM"),
        @JsonSubTypes.Type(value = PatchLandRequest.class, name = "LAND")
})
public sealed interface DraftPatchRequest permits PatchCattleRequest, PatchFarmRequest, PatchLandRequest {
    ProductTypeEnum productType();
    String name();
    BigDecimal price();
    String description();
    Double latitude();
    Double longitude();
}