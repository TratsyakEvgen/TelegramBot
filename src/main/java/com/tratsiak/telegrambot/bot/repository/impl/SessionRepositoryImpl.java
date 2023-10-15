package com.tratsiak.telegrambot.bot.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;
import com.tratsiak.telegrambot.bot.repository.AbstractHttpClient;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.http.HttpClient;
import java.util.List;

@Repository
public class SessionRepositoryImpl extends AbstractHttpClient implements SessionRepository {

    @Autowired
    public SessionRepositoryImpl(HttpClient httpClient,
                                 ObjectMapper objectMapper,
                                 @Value("${bot.manager.uri}") String uri) {
        super(httpClient, objectMapper, uri);
    }

    @Override
    public List<Session> getSessions() throws RepositoryException {
        return sendGet("employees?status=true", Session[].class);
    }
}
