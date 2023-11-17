package com.tratsiak.telegrambot.bot.repository.impl;

import com.tratsiak.telegrambot.bot.model.Activation;
import com.tratsiak.telegrambot.bot.model.InfoByActivation;
import com.tratsiak.telegrambot.bot.model.StatusByContract;
import com.tratsiak.telegrambot.bot.repository.AssomiRepository;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClient;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class AssomiRepositoryImpl implements AssomiRepository {

    private final RepositoryHttpClient client;
    private final String uri;

    public AssomiRepositoryImpl(RepositoryHttpClient client, @Value("${gateway}") String uri) {
        this.client = client;
        this.uri = uri;
    }


    @Override
    public void activate(Activation activation) throws RepositoryException {
        try {
            client.post(uri + "/AssomiParser/assomi/activation", activation, 30L);
        } catch (RepositoryHttpClientException e) {
            throw new RepositoryException(String.format("Failed activation %s", activation.toString()), e);
        }
    }


    @Override
    public StatusByContract getStatus(String numberOfContract) throws RepositoryException {
        try {
            return client.get(uri + "/AssomiParser/assomi/status/" + numberOfContract, StatusByContract.class);
        } catch (RepositoryHttpClientException e) {
            throw new RepositoryException(String.format("Failed to get status contract %s", numberOfContract), e);
        }
    }

    @Override
    public InfoByActivation getInfoByActivation(String numberOfContract) throws RepositoryException {
        try {
            return client.get(uri + "/AssomiParser/assomi/activation/" + numberOfContract, InfoByActivation.class);
        } catch (RepositoryHttpClientException e) {
            throw new RepositoryException(String.format("Failed to get activation info contract %s", numberOfContract), e);
        }
    }
}
