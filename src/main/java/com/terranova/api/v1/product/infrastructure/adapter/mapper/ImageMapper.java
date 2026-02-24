package com.terranova.api.v1.product.infrastructure.adapter.mapper;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.command.CreateImageCommand;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image entityToDomain(ImageEntity entity);

    ImageEntity domainToEntity(CreateImageCommand image);

}
