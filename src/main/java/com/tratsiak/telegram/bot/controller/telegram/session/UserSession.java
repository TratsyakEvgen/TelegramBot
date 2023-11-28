package com.tratsiak.telegram.bot.controller.telegram.session;

import com.tratsiak.telegram.bot.service.ServiceException;

import java.util.Optional;

public interface UserSession {
    Optional<Session> getSession(long id);

    void initSessions() throws ServiceException;

}

