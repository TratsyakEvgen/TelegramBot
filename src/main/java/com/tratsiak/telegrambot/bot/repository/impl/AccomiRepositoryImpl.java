package com.tratsiak.telegrambot.bot.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.repository.AbstractHttpClient;
import com.tratsiak.telegrambot.bot.repository.AccomiRepository;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.http.HttpClient;

@Repository
public class AccomiRepositoryImpl extends AbstractHttpClient implements AccomiRepository {

    @Autowired
    public AccomiRepositoryImpl(HttpClient httpClient, ObjectMapper objectMapper, @Value("${accomi.uri}") String uri) {
        super(httpClient, objectMapper, uri);
    }

    @Override
    public void activate(Contract contract) throws RepositoryException {
        sendPost("activation", contract);
    }


    @Override
    public String getStatus(String contract) throws RepositoryException {
        return sendGet("status/" + contract);
    }
}
