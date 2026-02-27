package com.terranova.api.v1.product.domain.factory;

import com.terranova.api.v1.product.domain.model.Cattle;
import com.terranova.api.v1.product.domain.model.Farm;
import com.terranova.api.v1.product.domain.model.Land;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.CreateCattleCommand;
import com.terranova.api.v1.product.domain.model.command.CreateFarmCommand;
import com.terranova.api.v1.product.domain.model.command.CreateLandCommand;
import com.terranova.api.v1.product.domain.model.command.CreateProductCommand;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import java.time.LocalDate;
import java.util.UUID;

public class ProductFactory {

    public Product create(CreateProductCommand request) {
        UUID sellerId = request.idSeller();

        return switch (request.productType()){
            case FARM -> buildFarm((CreateFarmCommand) request, sellerId);
            case LAND -> buildLand((CreateLandCommand) request, sellerId);
            case CATTLE -> buildCattle((CreateCattleCommand) request, sellerId);
            default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED);
        };
    }

    private Cattle buildCattle(CreateCattleCommand request, UUID sellerId){
        var builder = Cattle.builder();
        buildBase(builder, request, sellerId, ProductTypeEnum.CATTLE);
        return builder
                .race(request.race())
                .weightInKg(request.weightInKg())
                .cattleAgeInYears(request.cattleAgeInYears())
                .gender(request.gender())
                .cattleType(request.cattleType())
                .quantity(request.quantity())
                .build();
    }

    private Farm buildFarm(CreateFarmCommand request, UUID sellerId){
        var builder = Farm.builder();
        buildBase(builder, request, sellerId, ProductTypeEnum.FARM);
        return builder
                .totalSpaceInM2(request.totalSpaceInM2())
                .builtSpaceInM2(request.builtSpaceInM2())
                .stratum(request.stratum())
                .roomsQuantity(request.roomsQuantity())
                .bathroomsQuantity(request.bathroomsQuantity())
                .build();
    }

    private Land buildLand(CreateLandCommand request, UUID sellerId){
        var builder = Land.builder();
        buildBase(builder, request, sellerId, ProductTypeEnum.LAND);
        return builder
                .landSizeInM2(request.landSizeInM2())
                .currentUse(request.currentUse())
                .topography(request.topography())
                .access(request.access())
                .currentServices(request.currentServices())
                .build();
    }

    private void buildBase(Product.ProductBuilder<?, ?> builder, CreateProductCommand request, UUID sellerId, ProductTypeEnum productType){
        builder
                .productType(productType)
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .status("IMAGE_PENDING")
                .publishDate(LocalDate.now())
                .city(request.city())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .sellerId(sellerId);
    }
}
