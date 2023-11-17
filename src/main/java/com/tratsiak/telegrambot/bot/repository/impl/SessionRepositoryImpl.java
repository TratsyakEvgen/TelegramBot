package com.tratsiak.telegrambot.bot.repository.impl;

import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.repository.SessionRepository;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClient;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    private final RepositoryHttpClient client;
    private final String uri;

    public SessionRepositoryImpl(RepositoryHttpClient client, @Value("${gateway}") String uri) {
        this.client = client;
        this.uri = uri;
    }

    @Override
    public List<Session> getSessions() throws RepositoryException {
        try {
            return client.getList(uri + "/BotManager/employees?status=true", Session[].class);
        } catch (RepositoryHttpClientException e) {
            throw new RepositoryException("Failed get sessions", e);
        }
    }
}
