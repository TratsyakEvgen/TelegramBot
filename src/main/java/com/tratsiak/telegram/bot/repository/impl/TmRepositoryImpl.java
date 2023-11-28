package com.tratsiak.telegram.bot.repository.impl;

import com.tratsiak.telegram.bot.repository.RepositoryException;
import com.tratsiak.telegram.bot.repository.TmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class TmRepositoryImpl implements TmRepository {

    private final WebClient client;

    @Autowired
    public TmRepositoryImpl(WebClient client) {
        this.client = client;
    }

    @Override
    public String getStatus(String numberOfContract) throws RepositoryException {
        try {
            return client.get()
                    .uri(uriBuilder -> uriBuilder.path("/TaskManagerParser/tm/status/" + numberOfContract).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, error -> Mono.error(
                            new RuntimeException(String.valueOf(error.statusCode()))))
                    .bodyToMono(String.class)
                    .block();
        } catch (RuntimeException e) {
            throw new RepositoryException(String.format("Failed to get status contract %s", numberOfContract), e);
        }
    }

}
