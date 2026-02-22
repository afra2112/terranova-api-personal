package com.terranova.api.v1.product.infrastructure.adapter.mapper;

import com.terranova.api.v1.product.domain.model.Cattle;
import com.terranova.api.v1.product.domain.model.Farm;
import com.terranova.api.v1.product.domain.model.Land;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.CreateCattleCommand;
import com.terranova.api.v1.product.domain.model.command.CreateFarmCommand;
import com.terranova.api.v1.product.domain.model.command.CreateLandCommand;
import com.terranova.api.v1.product.domain.model.command.CreateProductCommand;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.CreateCattleRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.CreateFarmRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.CreateLandRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.CreateProductRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CreateCattleResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CreateFarmResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CreateLandResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CreateProductResponse;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.CattleEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.FarmEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.LandEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

        @SubclassMapping(source = CreateFarmRequest.class, target = CreateFarmCommand.class)
        @SubclassMapping(source = CreateCattleRequest.class, target = CreateCattleCommand.class)
        @SubclassMapping(source = CreateLandRequest.class, target = CreateLandCommand.class)
        CreateProductCommand fromRequestToCommand(CreateProductRequest product);

        @SubclassMapping(source = FarmEntity.class, target = Farm.class)
        @SubclassMapping(source = CattleEntity.class, target = Cattle.class)
        @SubclassMapping(source = LandEntity.class, target = Land.class)
        Product fromEntityToDomain(ProductEntity entity);

        @SubclassMapping(source = Farm.class, target = FarmEntity.class)
        @SubclassMapping(source = Cattle.class, target = CattleEntity.class)
        @SubclassMapping(source = Land.class, target = LandEntity.class)
        ProductEntity fromDomainToEntity(Product product);

        @SubclassMapping(source = Farm.class, target = CreateFarmResponse.class)
        @SubclassMapping(source = Cattle.class, target = CreateCattleResponse.class)
        @SubclassMapping(source = Land.class, target = CreateLandResponse.class)
        CreateProductResponse toDTO(Product product);
}
