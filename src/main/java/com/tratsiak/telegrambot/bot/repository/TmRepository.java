package com.tratsiak.telegrambot.bot.repository;

public interface TmRepository {
    String getStatus(String contract) throws RepositoryException;
}
