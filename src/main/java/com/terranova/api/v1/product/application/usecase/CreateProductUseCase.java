package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.factory.ProductFactory;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.CreateProductCommand;
import com.terranova.api.v1.product.domain.model.group.CattleGroup;
import com.terranova.api.v1.product.domain.model.group.FarmGroup;
import com.terranova.api.v1.product.domain.model.group.LandGroup;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;

public class CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ValidatorPort validatorPort;
    private final ProductFactory productFactory;

    public CreateProductUseCase(ProductRepositoryPort productRepositoryPort, ValidatorPort validatorPort, ProductFactory productFactory) {
        this.productRepositoryPort = productRepositoryPort;
        this.validatorPort = validatorPort;
        this.productFactory = productFactory;
    }

    public Product createProduct(CreateProductCommand createProductCommand){
        validatorPort.validate(createProductCommand, getGroupFromRequestProductType(createProductCommand.productType()));
        return productRepositoryPort.save(productFactory.create(createProductCommand));
    }

    private Class<?> getGroupFromRequestProductType(String productType){
        return switch (productType){
            case "FARM" -> FarmGroup.class;
            case "LAND" -> LandGroup.class;
            case "CATTLE" -> CattleGroup.class;
            default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED);
        };
    }
}
