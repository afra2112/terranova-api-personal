package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.factory.ProductFactory;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.CreateProductCommand;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;

public class CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductFactory productFactory;

    public CreateProductUseCase(ProductRepositoryPort productRepositoryPort , ProductFactory productFactory) {
        this.productRepositoryPort = productRepositoryPort;
        this.productFactory = productFactory;
    }

    public Product createProduct(CreateProductCommand createProductCommand){
        return productRepositoryPort.save(productFactory.create(createProductCommand));
    }
}
