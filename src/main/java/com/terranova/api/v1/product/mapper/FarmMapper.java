package com.terranova.api.v1.product.mapper;

import com.terranova.api.v1.product.dto.createResponse.CreateFarmResponse;
import com.terranova.api.v1.product.entity.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    @Mapping(target = "productType", constant = "FARM")
    CreateFarmResponse toDTO(Farm farm);
}
