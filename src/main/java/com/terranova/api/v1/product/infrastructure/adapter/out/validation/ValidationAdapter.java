package com.terranova.api.v1.product.infrastructure.adapter.out.validation;

import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@AllArgsConstructor
public class ValidationAdapter implements ValidatorPort {

    private final Validator validator;

    @Override
    public void validate(Object validationTarget, Class<?> group) {
        Set<ConstraintViolation<Object>> violations = validator.validate(validationTarget, group);
        Set<ConstraintViolation<Object>> violationsDefault = validator.validate(validationTarget, Default.class);

        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        if(!violationsDefault.isEmpty()){
            throw new ConstraintViolationException(violationsDefault);
        }
    }
}
