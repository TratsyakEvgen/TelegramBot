package com.tratsiak.telegrambot.bot.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.repository.AbstractHttpClient;
import com.tratsiak.telegrambot.bot.repository.LogRepository;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.http.HttpClient;


@Repository
public class LogRepositoryImpl extends AbstractHttpClient implements LogRepository {

    @Autowired
    public LogRepositoryImpl(HttpClient httpClient, ObjectMapper objectMapper, @Value("${bot.manager.uri}") String uri) {
        super(httpClient, objectMapper, uri);
    }

    @Override
    public void create(Contract contract) throws RepositoryException {
        sendPost("histories", contract);
    }
}
