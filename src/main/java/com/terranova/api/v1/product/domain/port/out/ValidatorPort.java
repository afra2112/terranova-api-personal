package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.command.CreateProductCommand;

public interface ValidatorPort {

    void validate(CreateProductCommand createProductCommand, Class<?> group);
}
