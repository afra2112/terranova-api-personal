package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.Product;

public interface ValidatorPort {

    void validate(Object validationTarget, Class<?> group);

    void validatePublish(Product product);
}
