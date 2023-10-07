package com.tratsiak.telegrambot.bot.controller.session.impl;

import com.tratsiak.telegrambot.bot.controller.session.Session;
import com.tratsiak.telegrambot.bot.controller.session.UserSession;
import com.tratsiak.telegrambot.bot.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserSessionImpl implements UserSession {

    @Autowired
    private SessionService service;
    private ConcurrentHashMap<Long, Session> session;

    public Optional<Session> getSession(long id){
        return Optional.ofNullable(session.get(id));
    }

    public void putSession(Session session){
        this.session.put(session.getUserId(), session);
    }

    public void removeSession(long id){
        session.remove(id);
    }

    public void initSession(){
        session = new ConcurrentHashMap<>(service.getSessions());
    }

}
