package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;

import java.util.List;

public class ChangeCoverImageUseCase {

    private final ProductOwnershipValidatorPort ownershipValidator;
    private final ImageRepositoryPort imageRepositoryPort;

    public ChangeCoverImageUseCase(
            ProductOwnershipValidatorPort ownershipValidator,
            ImageRepositoryPort imageRepositoryPort
    ) {
        this.ownershipValidator = ownershipValidator;
        this.imageRepositoryPort = imageRepositoryPort;
    }

    public void setCoverImage(
            Long productId,
            Long imageId
    ){

        ownershipValidator.validateOwnership(productId);

        List<Image> images =
                imageRepositoryPort.getByProductId(productId);

        boolean exists =
                images.stream()
                        .anyMatch(i ->
                                i.idImage().equals(imageId)
                        );

        if(!exists){
            throw new BusinessException(
                    ErrorCodeEnum.ENTITY_NOT_FOUND,
                    "Image not found: " + imageId
            );
        }

        imageRepositoryPort.setCoverImage(
                productId,
                imageId
        );
    }
}