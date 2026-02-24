package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.command.CreateImageCommand;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import java.util.List;

public class CreateImageUseCase {

    private final ImageRepositoryPort imageRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public CreateImageUseCase(ImageRepositoryPort imageRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        this.imageRepositoryPort = imageRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Image> createImages(List<CreateImageCommand> imagesCommands, Long imageId){
        if(!productRepositoryPort.existsById(imageId)){
            throw new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Product not found with id: " + imageId);
        }

        List<Image> savedImagesDomain = imageRepositoryPort.save(imagesCommands);
        //call external storage management port

        return savedImagesDomain;
    }
}