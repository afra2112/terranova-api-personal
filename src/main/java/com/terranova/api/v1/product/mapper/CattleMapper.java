package com.terranova.api.v1.product.mapper;

import com.terranova.api.v1.product.dto.createResponse.CreateCattleResponse;
import com.terranova.api.v1.product.entity.Cattle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CattleMapper {

    @Mapping(target = "productType", constant = "CATTLE")
    CreateCattleResponse toDTO(Cattle cattle);
}
