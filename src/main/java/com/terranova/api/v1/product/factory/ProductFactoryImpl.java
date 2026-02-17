package com.terranova.api.v1.product.factory;

import com.terranova.api.v1.product.dto.createRequest.CreateCattleRequest;
import com.terranova.api.v1.product.dto.createRequest.CreateFarmRequest;
import com.terranova.api.v1.product.dto.createRequest.CreateLandRequest;
import com.terranova.api.v1.product.dto.createRequest.CreateProductRequest;
import com.terranova.api.v1.product.entity.Cattle;
import com.terranova.api.v1.product.entity.Farm;
import com.terranova.api.v1.product.entity.Land;
import com.terranova.api.v1.product.entity.Product;
import com.terranova.api.v1.product.enums.ProductType;
import com.terranova.api.v1.product.enums.StatusEnum;
import com.terranova.api.v1.user.entity.User;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(CreateProductRequest request, User seller) {
        return switch (request.productType()){
            case FARM -> buildFarm((CreateFarmRequest) request, seller);
            case LAND -> buildLand((CreateLandRequest) request, seller);
            case CATTLE -> buildCattle((CreateCattleRequest) request, seller);
        };
    }

    private Cattle buildCattle(CreateCattleRequest request, User seller){
        buildBase(Cattle.builder(), request, seller);

        return Cattle.builder()
                .race(request.race())
                .weight(request.weightInKg())
                .cattleAgeInYears(request.cattleAgeInYears())
                .gender(request.gender())
                .type(request.cattleType())
                .quantity(request.quantity())
                .productType(ProductType.CATTLE)
                .build();
    }

    private Farm buildFarm(CreateFarmRequest request, User seller){
        buildBase(Farm.builder(), request, seller);

        return Farm.builder()
                .totalSpaceInM2(request.totalSpaceInM2())
                .builtSpaceInM2(request.builtSpaceInM2())
                .stratum(request.stratum())
                .roomsQuantity(request.roomsQuantity())
                .bathroomsQuantity(request.bathroomsQuantity())
                .productType(ProductType.FARM)
                .build();
    }

    private Land buildLand(CreateLandRequest request, User seller){
        buildBase(Land.builder(), request, seller);

        return Land.builder()
                .landSizeInM2(request.landSizeInM2())
                .currentUse(request.currentUse())
                .topography(request.topography())
                .access(request.access())
                .currentServices(request.currentServices())
                .productType(ProductType.LAND)
                .build();
    }

    private void buildBase(Product.ProductBuilder<?, ?> builder, CreateProductRequest request, User seller){
            builder
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .status(StatusEnum.IMAGE_PENDING)
                .publishDate(LocalDate.now())
                .city(request.city())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .seller(seller);
    }
}
