package com.terranova.api.v1.product.infrastructure.adapter.out.validation;

import com.terranova.api.v1.product.domain.model.command.CreateProductCommand;
import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidationAdapter implements ValidatorPort {

    private final Validator validator;

    @Override
    public void validate(CreateProductCommand createProductCommand, Class<?> group) {
        validator.validate(createProductCommand, group);
    }
}
