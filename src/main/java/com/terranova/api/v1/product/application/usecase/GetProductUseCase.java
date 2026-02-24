package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;

import java.util.List;

public class GetProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ImageRepositoryPort imageRepositoryPort;

    public GetProductUseCase(ProductRepositoryPort productRepositoryPort, ImageRepositoryPort imageRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.imageRepositoryPort = imageRepositoryPort;
    }

    public Product getProduct(Long productId){
        Product product = productRepositoryPort.getById(productId).orElseThrow(
                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Product not found by id: " + productId)
        );

        List<Image> images = imageRepositoryPort.getByProductId(productId);

        return product.withImages(images);
    }
}
