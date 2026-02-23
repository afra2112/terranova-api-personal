package com.terranova.api.v1.product.domain.port.out;

public interface ValidatorPort {

    void validate(Object validationTarget, Class<?> group);
}
