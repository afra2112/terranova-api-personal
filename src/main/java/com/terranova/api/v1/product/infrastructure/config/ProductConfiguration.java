package com.terranova.api.v1.product.infrastructure.config;

import com.terranova.api.v1.product.application.usecase.CreateImageUseCase;
import com.terranova.api.v1.product.application.usecase.CreateProductUseCase;
import com.terranova.api.v1.product.domain.factory.ProductFactory;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ImageStoragePort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductConfiguration {

    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepositoryPort productRepositoryPort, ProductFactory productFactory){
        return new CreateProductUseCase(
                productRepositoryPort,
                productFactory
        );
    }

    @Bean
    public ProductFactory productFactory(){
        return new ProductFactory();
    }

    @Bean
    public CreateImageUseCase createImageUseCase(ImageRepositoryPort imageRepositoryPort, ProductRepositoryPort productRepositoryPort, ImageStoragePort imageStoragePort){
        return new CreateImageUseCase(imageStoragePort, imageRepositoryPort, productRepositoryPort);
    }
}
