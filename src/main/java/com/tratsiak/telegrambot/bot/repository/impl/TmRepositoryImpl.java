package com.tratsiak.telegrambot.bot.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegrambot.bot.repository.AbstractHttpClient;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.repository.TmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Repository
public class TmRepositoryImpl extends AbstractHttpClient implements TmRepository {

    @Autowired
    public TmRepositoryImpl(HttpClient httpClient, ObjectMapper objectMapper,@Value("${tm.uri}") String uri) {
        super(httpClient, objectMapper, uri);
    }

    @Override
    public String getStatus(String contract) throws RepositoryException {
        return sendGet("status/" + contract);
    }

}
