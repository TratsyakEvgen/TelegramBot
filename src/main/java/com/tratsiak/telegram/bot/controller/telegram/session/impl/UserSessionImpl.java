package com.tratsiak.telegram.bot.controller.telegram.session.impl;

import com.tratsiak.telegram.bot.controller.telegram.session.Session;
import com.tratsiak.telegram.bot.controller.telegram.session.UserSession;
import com.tratsiak.telegram.bot.service.ServiceException;
import com.tratsiak.telegram.bot.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserSessionImpl implements UserSession {
    private final SessionService service;
    private ConcurrentHashMap<Long, Session> session;

    @Autowired
    public UserSessionImpl(SessionService service) {
        this.service = service;
    }

    public Optional<Session> getSession(long id) {
        return Optional.ofNullable(session.get(id));
    }


    public void initSessions() throws ServiceException {
        session = new ConcurrentHashMap<>(service.getSessions());
    }

}
