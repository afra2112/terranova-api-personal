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

    public ChangeCoverImageUseCase(ProductOwnershipValidatorPort ownershipValidator, ImageRepositoryPort imageRepositoryPort) {
        this.ownershipValidator = ownershipValidator;
        this.imageRepositoryPort = imageRepositoryPort;
    }

    public void changeCover(
            Long productId,
            Long imageId
    ){

        ownershipValidator.validateOwnership(productId);

        List<Image> images =
                imageRepositoryPort.getByProductId(productId);

        boolean exists =
                images.stream()
                        .anyMatch(
                                image ->
                                        image.idImage()
                                                .equals(imageId)
                        );

        if(!exists){
            throw new BusinessException(
                    ErrorCodeEnum.ENTITY_NOT_FOUND,
                    "Image not found by id: " + imageId
            );
        }

        List<Image> updated =
                images.stream()
                        .map(image ->
                                new Image(
                                        image.idImage(),
                                        image.fileName(),
                                        image.url(),
                                        image.contentType(),
                                        image.size(),
                                        image.displayOrder(),
                                        image.idImage().equals(imageId),
                                        image.createdAt(),
                                        image.productId()
                                )
                        )
                        .toList();

        imageRepositoryPort.saveAll(updated);
    }
}