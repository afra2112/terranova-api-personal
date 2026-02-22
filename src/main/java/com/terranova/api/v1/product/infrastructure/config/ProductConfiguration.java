package com.terranova.api.v1.product.infrastructure.config;

import com.terranova.api.v1.product.application.usecase.CreateProductUseCase;
import com.terranova.api.v1.product.domain.factory.ProductFactory;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductConfiguration {

    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepositoryPort productRepositoryPort, ValidatorPort validatorPort, ProductFactory productFactory){
        return new CreateProductUseCase(
                productRepositoryPort,
                validatorPort,
                productFactory
        );
    }

    @Bean
    public ProductFactory productFactory(){
        return new ProductFactory();
    }
}
