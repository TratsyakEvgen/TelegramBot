package com.tratsiak.telegrambot.bot.service.impl;

import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.repository.SessionRepository;
import com.tratsiak.telegrambot.bot.service.ServiceException;
import com.tratsiak.telegrambot.bot.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Map<Long, Session> getSessions() throws ServiceException {
        try {
            return sessionRepository.getSessions().stream()
                    .collect(Collectors.toMap(Session::getUserId, Function.identity()));
        } catch (RepositoryException e) {
            throw new ServiceException("Failed get sessions", e);
        }
    }
}
