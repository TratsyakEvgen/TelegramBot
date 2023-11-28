package com.tratsiak.telegram.bot.repository;

public interface TmRepository {
    String getStatus(String numberOfContract) throws RepositoryException;
}
