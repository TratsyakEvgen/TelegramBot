package com.tratsiak.telegrambot.bot.controller.session;

import java.util.Optional;

public interface UserSession {
    Optional<Session> getSession(long id);
    void putSession(Session session);
    void removeSession(long id);
    void initSession();

}

