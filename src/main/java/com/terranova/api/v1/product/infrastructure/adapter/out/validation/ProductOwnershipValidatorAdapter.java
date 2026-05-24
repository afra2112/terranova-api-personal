package com.terranova.api.v1.product.infrastructure.adapter.out.validation;

import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.shared.domain.port.ProductOwnershipValidatorPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductOwnershipValidatorAdapter implements ProductOwnershipValidatorPort {

    private final ProductRepositoryPort productRepositoryPort;
    private final AuthFacade authFacade;

    @Override
    public Product validateOwnership(Long productId) {

        Product product = productRepositoryPort.getById(productId).orElseThrow(
                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Product not found by id: " + productId)
        );
        UUID userId = authFacade.getAuthenticatedId();

        if (!product.getSellerId().equals(userId)){
            throw new BusinessException(ErrorCodeEnum.UNAUTHORIZED, "The product: " + productId + ". Doesn't belongs to the user: " + userId);
        }

        return product;
    }
}
