package com.tratsiak.telegram.bot.util.valodation.impl;

import com.tratsiak.telegram.bot.util.valodation.ObjectValidator;
import com.tratsiak.telegram.bot.util.valodation.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ObjectValidatorImpl implements ObjectValidator {
    private final Validator validator;

    @Autowired
    public ObjectValidatorImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> void validate(T obj) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validator.validate(obj);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
                sb.append("\n");
            }
            throw new ValidationException(String.valueOf(sb));
        }
    }
}
