package com.tratsiak.telegrambot.bot.repository;

import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;

import java.util.List;

public interface SessionRepository {
    List<Session> getSessions() throws RepositoryException;
}
