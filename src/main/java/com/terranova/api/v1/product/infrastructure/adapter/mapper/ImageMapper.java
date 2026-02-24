package com.terranova.api.v1.product.infrastructure.adapter.mapper;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.ImageResponse;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "productId", source = "product.productId")
    Image entityToDomain(ImageEntity entity);

    ImageEntity domainToEntity(Image image);

    ImageResponse domainToResponse(Image image);
}
