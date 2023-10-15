package com.tratsiak.telegrambot.bot.util.valodation.impl;

import com.tratsiak.telegrambot.bot.util.valodation.ValidationException;
import com.tratsiak.telegrambot.bot.util.valodation.ObjectValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ObjectValidatorImpl implements ObjectValidator {

    @Autowired
    private Validator validator;
    @Override
    public <T> void validate(T obj) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validator.validate(obj);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
                sb.append("\n");
            }
            throw new ValidationException("Неверный формат данных: " + sb);
        }
    }
}
