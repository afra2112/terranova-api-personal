package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.command.CreateImageCommand;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ImageStoragePort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CloudinaryResponse;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateImageUseCase {

    private final ImageStoragePort imageStoragePort;
    private final ImageRepositoryPort imageRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public CreateImageUseCase(ImageStoragePort imageStoragePort, ImageRepositoryPort imageRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        this.imageStoragePort = imageStoragePort;
        this.imageRepositoryPort = imageRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Image> createImages(List<CreateImageCommand> imagesCommands, Long productId){

        if(!productRepositoryPort.existsById(productId)){
            throw new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Product not found by id: " + productId);
        }

        List<String> publicSavedImagesIds = new ArrayList<>();

        try {
            List<Image> imagesDomain = imagesCommands.stream()
                .map(command -> {
                        String completeName = generateCompleteFileName(command.originalFilename());

                        CloudinaryResponse cloudinaryResponse = imageStoragePort.uploadToCloudinary(command.content(), completeName);
                        publicSavedImagesIds.add(completeName);

                        return buildImage(completeName, command, cloudinaryResponse, productId);
                })
                .toList();

            return imageRepositoryPort.save(imagesDomain);

        } catch (Exception e) {
            publicSavedImagesIds.forEach(imageStoragePort::deleteImages);
            throw e;
        }

    }

    private String generateCompleteFileName(String originalFileName){
        return UUID.randomUUID() + "-" + originalFileName;
    }

    private Image buildImage(String completeName, CreateImageCommand command, CloudinaryResponse cloudinaryResponse, Long productId){
        return new Image(
                null,
                completeName,
                cloudinaryResponse.url(),
                cloudinaryResponse.format(),
                command.size(),
                command.displayOrder(),
                LocalDateTime.now(),
                productId
        );
    }
}