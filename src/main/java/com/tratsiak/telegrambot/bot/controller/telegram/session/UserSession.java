package com.tratsiak.telegrambot.bot.controller.telegram.session;

import com.tratsiak.telegrambot.bot.service.ServiceException;

import java.util.Optional;

public interface UserSession {
    Optional<Session> getSession(long id);

    void putSession(Session session);

    void removeSession(long id);

    void initSession() throws ServiceException;

}

