package com.terranova.api.v1.shared.domain.port;

import com.terranova.api.v1.product.domain.model.Product;

public interface ProductOwnershipValidatorPort {

    Product validateOwnership(Long productId);
}
