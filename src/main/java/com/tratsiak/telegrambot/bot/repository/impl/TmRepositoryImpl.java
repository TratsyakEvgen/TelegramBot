package com.tratsiak.telegrambot.bot.repository.impl;

import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.repository.TmRepository;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClient;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class TmRepositoryImpl implements TmRepository {

    private final RepositoryHttpClient client;
    private final String uri;

    @Autowired
    public TmRepositoryImpl(RepositoryHttpClient client, @Value("${gateway}") String uri1) {
        this.client = client;
        this.uri = uri1;
    }

    @Override
    public String getStatus(String numberOfContract) throws RepositoryException {
        try {
            return client.getString(uri + "/TaskManagerParser/tm/status/" + numberOfContract);
        } catch (RepositoryHttpClientException e) {
            throw new RepositoryException(String.format("Failed to get status contract %s", numberOfContract), e);
        }
    }

}
