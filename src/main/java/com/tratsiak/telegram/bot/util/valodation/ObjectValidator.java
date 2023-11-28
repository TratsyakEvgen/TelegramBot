package com.tratsiak.telegram.bot.util.valodation;

public interface ObjectValidator {
    <T> void validate(T obj) throws ValidationException;
}
