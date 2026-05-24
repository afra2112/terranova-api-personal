package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.factory.ProductFactory;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.draft.patch.PatchProductCommand;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;

public class PatchProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductOwnershipValidatorPort productOwnershipValidatorPort;

    public PatchProductUseCase(ProductRepositoryPort productRepositoryPort, ProductOwnershipValidatorPort productOwnershipValidatorPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.productOwnershipValidatorPort = productOwnershipValidatorPort;
    }

    public Product patch(PatchProductCommand command, Long productId){
        Product product = productOwnershipValidatorPort.validateOwnership(productId);

        if (!product.getProductType().equals(command.productType())){
            throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_CANNOT_BE_CHANGED, "Current product type: " + product.getProductType() + ". Cannot be changed to: " + command.productType());
        }

        return productRepositoryPort.save(product.patch(command));
    }
}
