package com.tratsiak.telegrambot.bot.repository;

public interface TmRepository {
    String getStatus(String numberOfContract) throws RepositoryException;
}
