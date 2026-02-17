package com.terranova.api.v1.product.mapper;

import com.terranova.api.v1.product.dto.createResponse.CreateLandResponse;
import com.terranova.api.v1.product.entity.Land;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LandMapper {

    @Mapping(target = "productType", constant = "LAND")
    CreateLandResponse toDTO(Land land);
}
