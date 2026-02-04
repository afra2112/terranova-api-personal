package com.terranova.api.v1.product.mapper;

import com.terranova.api.v1.product.dto.CreateCattleRequest;
import com.terranova.api.v1.product.dto.CreateFarmRequest;
import com.terranova.api.v1.product.dto.CreateLandRequest;
import com.terranova.api.v1.product.entity.Cattle;
import com.terranova.api.v1.product.entity.Farm;
import com.terranova.api.v1.product.entity.Land;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public CreateCattleRequest toCattle(Cattle entity){
        return new CreateCattleRequest(
                entity.getRace(),
                entity.getWeight(),
                entity.getCattleAgeInYears(),
                entity.getGender(),
                entity.getType(),
                entity.getQuantity()
        );
    }

    public CreateFarmRequest toFarm(Farm entity){
        return new CreateFarmRequest(
                entity.getTotalSpaceInM2(),
                entity.getBuildedSpaceInM2(),
                entity.getStratum(),
                entity.getRoomsQuantity(),
                entity.getBathroomsQuantity()
        );
    }

    public CreateLandRequest toLand(Land entity){
        return new CreateLandRequest(
                entity.getLandSizeInM2(),
                entity.getCurrentUse(),
                entity.getTopography(),
                entity.getAccess(),
                entity.getCurrentServices()
        );
    }
}
