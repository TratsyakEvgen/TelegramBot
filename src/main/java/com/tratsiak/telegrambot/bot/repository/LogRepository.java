package com.tratsiak.telegrambot.bot.repository;

import com.tratsiak.telegrambot.bot.model.Contract;

public interface LogRepository {

    void create(Contract contract) throws RepositoryException;
}
