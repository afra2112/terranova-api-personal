package com.terranova.api.v1.product.mapper;

import com.terranova.api.v1.product.dto.createResponse.CreateProductResponse;
import com.terranova.api.v1.product.entity.Cattle;
import com.terranova.api.v1.product.entity.Farm;
import com.terranova.api.v1.product.entity.Land;
import com.terranova.api.v1.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

@RequiredArgsConstructor
@Mapper(
        componentModel = "spring",
        uses = {
                LandMapper.class,
                FarmMapper.class,
                CattleMapper.class
        }
)
public abstract class ProductMapper {

    protected LandMapper landMapper;
    protected FarmMapper farmMapper;
    protected CattleMapper cattleMapper;

    public CreateProductResponse toDTO(Product product){
        return switch (product.getProductType()){
            case FARM -> farmMapper.toDTO((Farm) product);
            case CATTLE -> cattleMapper.toDTO((Cattle) product);
            case LAND -> landMapper.toDTO((Land) product);
        };
    }
}
