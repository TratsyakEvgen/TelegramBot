package com.tratsiak.telegrambot.bot.service.impl;

import com.tratsiak.telegrambot.bot.controller.session.Session;
import com.tratsiak.telegrambot.bot.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SessionServiceImpl implements SessionService {
    @Override
    public Map<Long, Session> getSessions() {
        long id = 781198053L;
        Map<Long, Session> sessions = new HashMap<>();
        sessions.put(id, Session.builder().userId(id).name("Eвгений").build());
        sessions.put(1000562197L, Session.builder().userId(1000562197).name("Коза").build());
        return sessions;
    }
}
