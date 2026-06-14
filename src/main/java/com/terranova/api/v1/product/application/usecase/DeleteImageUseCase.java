package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ImageStoragePort;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DeleteImageUseCase {

    private final ProductOwnershipValidatorPort productOwnershipValidatorPort;
    private final ImageRepositoryPort imageRepositoryPort;
    private final ImageStoragePort imageStoragePort;

    public DeleteImageUseCase(ProductOwnershipValidatorPort productOwnershipValidatorPort, ImageRepositoryPort imageRepositoryPort, ImageStoragePort imageStoragePort) {
        this.productOwnershipValidatorPort = productOwnershipValidatorPort;
        this.imageRepositoryPort = imageRepositoryPort;
        this.imageStoragePort = imageStoragePort;
    }

    @Transactional
    public int deleteImages(
            Long productId,
            List<Long> imageIds
    ){

        // 1. Ownership validation
        Product product = productOwnershipValidatorPort
                .validateOwnership(productId);

        // 2. Load images and validate relationship
        List<Image> images =
                imageRepositoryPort
                        .getByProductIdAndIdIn(
                                productId,
                                imageIds
                        );

        if(images.size() != imageIds.size()){
            throw new BusinessException(
                    ErrorCodeEnum.ENTITY_NOT_FOUND,
                    "Some images were not found for product id: "
                            + productId
            );
        }

        // 3. Prevent product without images
        int currentImagesCount =
                imageRepositoryPort.countByProductId(productId);

        if(currentImagesCount - imageIds.size() < 1 && !product.getStatus().equals(StatusEnum.DRAFT)){
            throw new BusinessException(
                    ErrorCodeEnum.CANNOT_DELETE_ALL_IMAGES,
                    "Product must keep at least one image"
            );
        }

        // 4. Delete from Cloudinary first
        for(Image image : images){
            imageStoragePort.deleteImages(
                    image.fileName()
            );
        }

        // 5. Delete from database
        return imageRepositoryPort.deleteByProductIdAndIds(
                productId,
                imageIds
        );
    }
}
