package com.terranova.api.v1.product.infrastructure.config;

import com.terranova.api.v1.product.application.usecase.*;
import com.terranova.api.v1.product.domain.factory.ProductFactory;
import com.terranova.api.v1.product.domain.port.out.*;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

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

    @Bean
    public GetProductUseCase getProductUseCase(ProductRepositoryPort productRepositoryPort, ImageRepositoryPort imageRepositoryPort, AppointmentPort appointmentPort, UserPort userPort){
        return new GetProductUseCase(productRepositoryPort, imageRepositoryPort, appointmentPort, userPort);
    }

    @Bean
    public DeleteImageUseCase deleteImageUseCase(ImageRepositoryPort imageRepositoryPort, ImageStoragePort imageStoragePort){
        return new DeleteImageUseCase(imageRepositoryPort, imageStoragePort);
    }

    @Bean
    public CreateDraftUseCase createDraftUseCase(ProductRepositoryPort productRepositoryPort, AuthFacade authFacade, ProductFactory productFactory){
        return new CreateDraftUseCase(productRepositoryPort, productFactory, authFacade);
    }

    @Bean
    public PatchProductUseCase patchProductUseCase(ProductRepositoryPort productRepositoryPort, ProductOwnershipValidatorPort productOwnershipValidatorPort){
        return new PatchProductUseCase(productRepositoryPort, productOwnershipValidatorPort);
    }

    @Bean
    public PublishProductUseCase publishProductUseCase(ProductRepositoryPort productRepositoryPort, ProductOwnershipValidatorPort productOwnershipValidatorPort, ValidatorPort validatorPort, LocationPort locationPort, ImageRepositoryPort imageRepositoryPort){
        return new PublishProductUseCase(productRepositoryPort, productOwnershipValidatorPort, imageRepositoryPort, validatorPort, locationPort);
    }

    @Bean
    public RestClient restClient(){
        return RestClient.builder().build();
    }
}
