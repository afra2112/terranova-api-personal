package com.terranova.api.v1.product.infrastructure.adapter.out.validation;

import com.terranova.api.v1.product.domain.model.Cattle;
import com.terranova.api.v1.product.domain.model.Farm;
import com.terranova.api.v1.product.domain.model.Land;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@AllArgsConstructor
public class ValidationAdapter implements ValidatorPort {

    private final Validator validator;

    @Override
    public void validate(Object validationTarget, Class<?> group) {
        Set<ConstraintViolation<Object>> violations = validator.validate(validationTarget, group);
        Set<ConstraintViolation<Object>> violationsDefault = validator.validate(validationTarget, Default.class);

        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        if(!violationsDefault.isEmpty()){
            throw new ConstraintViolationException(violationsDefault);
        }
    }

    @Override
    public void validatePublish(Product product) {

        validateStatus(product);
        validateBaseFields(product);
        validateImages(product);

        switch (product.getProductType()){
            case CATTLE -> validateCattle((Cattle) product);
            case FARM -> validateFarm((Farm) product);
            case LAND -> validateLand((Land) product);
            default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED);
        }
    }

    private void validateStatus(Product product){
        if(product.getStatus() != StatusEnum.DRAFT){
            throw new BusinessException(
                    ErrorCodeEnum.WRONG_PRODUCT_STATUS,
                    "Status must be DRAFT"
            );
        }
    }

    private void validateBaseFields(Product product){

        requireNotBlank(product.getName(), "Product name required");

        requireNotNull(product.getPrice(), "Product price required");

        requireNotBlank(product.getDescription(), "Product description required");

        requireNotBlank(product.getCity(), "City required");

        requireNotNull(product.getLatitude(), "Latitude required");

        requireNotNull(product.getLongitude(), "Longitude required");
    }

    private void validateImages(Product product){
        if(product.getImages() == null || product.getImages().size() < 3){
            throw new BusinessException(
                    ErrorCodeEnum.PUBLISH_VALIDATION_ERROR,
                    "Product must have at least 3 images"
            );
        }
    }

    private void validateCattle(Cattle cattle){

        requireNotBlank(cattle.getRace(), "Race required");

        requirePositive(cattle.getWeightInKg(), "Weight required");

        requirePositive(cattle.getCattleAgeInYears(), "Age required");

        requireNotNull(cattle.getGender(), "Gender required");

        requireNotNull(cattle.getCattleType(), "Cattle type required");

        requirePositive(cattle.getQuantity(), "Quantity required");
    }

    private void validateFarm(Farm farm){

        requirePositive(
                farm.getTotalSpaceInM2(),
                "Total space required"
        );

        requirePositive(
                farm.getBuiltSpaceInM2(),
                "Built space required"
        );

        requirePositive(
                farm.getStratum(),
                "Stratum required"
        );

        requirePositive(
                farm.getRoomsQuantity(),
                "Rooms quantity required"
        );

        requirePositive(
                farm.getBathroomsQuantity(),
                "Bathrooms quantity required"
        );
    }

    private void validateLand(Land land){

        requirePositive(
                land.getLandSizeInM2(),
                "Land size required"
        );

        requireNotBlank(
                land.getCurrentUse(),
                "Current use required"
        );

        requireNotNull(
                land.getTopography(),
                "Topography required"
        );

        requireNotNull(
                land.getAccess(),
                "Access required"
        );

        requireNotBlank(
                land.getCurrentServices(),
                "Current services required"
        );
    }

    private void requireNotNull(Object value, String message){
        if(value == null){
            throw new BusinessException(
                    ErrorCodeEnum.PUBLISH_VALIDATION_ERROR,
                    message
            );
        }
    }

    private void requireNotBlank(String value, String message){
        if(value == null || value.isBlank()){
            throw new BusinessException(
                    ErrorCodeEnum.PUBLISH_VALIDATION_ERROR,
                    message
            );
        }
    }

    private void requirePositive(Number value, String message){

        if(value == null){
            throw new BusinessException(
                    ErrorCodeEnum.PUBLISH_VALIDATION_ERROR,
                    message
            );
        }

        if(value.doubleValue() <= 0){
            throw new BusinessException(
                    ErrorCodeEnum.PUBLISH_VALIDATION_ERROR,
                    message
            );
        }
    }
}
