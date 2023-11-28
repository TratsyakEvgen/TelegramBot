package com.tratsiak.telegram.bot.repository;

import com.tratsiak.telegram.bot.controller.telegram.session.Session;

import java.util.List;

public interface SessionRepository {
    List<Session> getSessions() throws RepositoryException;
}
