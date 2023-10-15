package com.tratsiak.telegrambot.bot.repository;

import com.tratsiak.telegrambot.bot.model.Contract;

public interface AccomiRepository {

    void activate(Contract contract) throws RepositoryException;
    String getStatus(String contract) throws RepositoryException;

}
