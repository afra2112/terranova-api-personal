package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.publish.CreateImageCommand;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ImageStoragePort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.image.CloudinaryResponse;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CreateImageUseCase {

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/jpg",
            "image/png",
            "image/webp"
    );

    private static final int MAX_IMAGES = 18;

    private final ImageStoragePort imageStoragePort;
    private final ImageRepositoryPort imageRepositoryPort;
    private final ProductOwnershipValidatorPort productOwnershipValidatorPort;

    public CreateImageUseCase(ImageStoragePort imageStoragePort, ImageRepositoryPort imageRepositoryPort, ProductOwnershipValidatorPort productOwnershipValidatorPort) {
        this.imageStoragePort = imageStoragePort;
        this.imageRepositoryPort = imageRepositoryPort;
        this.productOwnershipValidatorPort = productOwnershipValidatorPort;
    }

    public List<Image> createImages(
            List<CreateImageCommand> commands,
            Long productId
    ) {

        Product product =
                productOwnershipValidatorPort
                        .validateOwnership(productId);

        validateProductStatus(product);

        validateImageLimit(productId, commands.size());

        List<String> uploadedPublicIds =
                new ArrayList<>();

        boolean coverAlreadyExists =
                imageRepositoryPort.existsCoverImage(productId);

        try {

            List<Image> images = new ArrayList<>();

            for (CreateImageCommand command : commands) {

                validateFormat(command);

                String completeName =
                        generateCompleteFileName(
                                command.originalFilename()
                        );

                CloudinaryResponse cloudinaryResponse =
                        imageStoragePort.uploadToCloudinary(
                                command.content(),
                                completeName
                        );

                uploadedPublicIds.add(completeName);

                boolean isCover = false;

                if (!coverAlreadyExists) {
                    isCover = true;
                    coverAlreadyExists = true;
                }

                images.add(
                        buildImage(
                                completeName,
                                command,
                                cloudinaryResponse,
                                productId,
                                isCover
                        )
                );
            }

            return imageRepositoryPort.save(
                    images,
                    productId
            );

        } catch (Exception e) {

            uploadedPublicIds.forEach(
                    imageStoragePort::deleteImages
            );

            throw e;
        }
    }

    private void validateProductStatus(Product product) {

        if (product.getStatus() == StatusEnum.ARCHIVED) {
            throw new BusinessException(
                    ErrorCodeEnum.WRONG_PRODUCT_STATUS,
                    "Archived products cannot be modified."
            );
        }

        if (product.getStatus() == StatusEnum.SOLD) {
            throw new BusinessException(
                    ErrorCodeEnum.WRONG_PRODUCT_STATUS,
                    "Sold products cannot be modified."
            );
        }
    }

    private void validateImageLimit(
            Long productId,
            int incomingImages
    ) {

        int currentImages =
                imageRepositoryPort.countByProductId(
                        productId
                );

        if (currentImages + incomingImages > MAX_IMAGES) {

            throw new BusinessException(
                    ErrorCodeEnum.IMAGE_LIMIT_EXCEEDED,
                    "Maximum allowed images: "
                            + MAX_IMAGES
            );
        }
    }

    private void validateFormat(
            CreateImageCommand command
    ) {

        if (!ALLOWED_TYPES.contains(
                command.contentType()
        )) {

            throw new BusinessException(
                    ErrorCodeEnum.INVALID_IMAGE_FORMAT,
                    "Unsupported image format: "
                            + command.contentType()
            );
        }
    }

    private String generateCompleteFileName(
            String originalFileName
    ) {

        return UUID.randomUUID()
                + "-"
                + originalFileName;
    }

    private Image buildImage(
            String completeName,
            CreateImageCommand command,
            CloudinaryResponse response,
            Long productId,
            boolean isCoverImage
    ) {

        return new Image(
                null,
                completeName,
                response.url(),
                response.format(),
                command.size(),
                command.displayOrder(),
                isCoverImage,
                LocalDateTime.now(),
                productId
        );
    }

    public Integer getCurrentMaxOrder(Long productId){
        return imageRepositoryPort.getMaxDisplayOrder(productId);
    }
}