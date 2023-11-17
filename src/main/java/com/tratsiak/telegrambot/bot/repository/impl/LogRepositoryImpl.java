package com.tratsiak.telegrambot.bot.repository.impl;

import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.repository.LogRepository;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClient;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class LogRepositoryImpl implements LogRepository {

    private final RepositoryHttpClient client;
    private final String uri;

    public LogRepositoryImpl(RepositoryHttpClient client, @Value("${gateway}") String uri) {
        this.client = client;
        this.uri = uri;
    }


    @Override
    public void create(Contract contract) throws RepositoryException {
        try {
            client.post(uri + "/BotManager/histories", contract);
        } catch (RepositoryHttpClientException e) {
            throw new RepositoryException(String.format("Failed send history %s", contract.toString()), e);
        }
    }
}
