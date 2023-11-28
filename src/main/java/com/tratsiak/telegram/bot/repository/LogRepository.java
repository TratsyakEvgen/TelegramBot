package com.tratsiak.telegram.bot.repository;

import com.tratsiak.telegram.bot.model.Contract;

public interface LogRepository {

    void create(Contract contract) throws RepositoryException;
}
