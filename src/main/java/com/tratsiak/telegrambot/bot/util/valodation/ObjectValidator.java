package com.tratsiak.telegrambot.bot.util.valodation;

public interface ObjectValidator {
    <T> void validate(T obj) throws ValidationException;
}
