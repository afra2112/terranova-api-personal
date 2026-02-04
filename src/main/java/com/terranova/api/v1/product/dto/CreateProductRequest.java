package com.terranova.api.v1.product.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.entity.Cattle;
import com.terranova.api.v1.product.entity.Farm;
import com.terranova.api.v1.product.entity.Land;
import com.terranova.api.v1.product.enums.StatusEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = Cattle.class, name = "CATTLE"),
                @JsonSubTypes.Type(value = Farm.class, name = "Farm"),
                @JsonSubTypes.Type(value = Land.class, name = "Land")
        }
)
public interface CreateProductRequest {
    @NotBlank
    String name();

    @NotNull
    @DecimalMin(value = "1", inclusive = true)
    @Digits(integer = 13, fraction = 0)
    BigDecimal price();

    @NotBlank
    String description();

    @NotNull
    StatusEnum status();

    @NotNull
    LocalDate publishDate();

    @NotNull
    String city();

    @NotNull
    @Positive
    Double latitude();

    @NotNull
    @Positive
    Double longitude();

    @NotNull
    Long idSeller();
}
