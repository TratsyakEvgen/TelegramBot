package com.tratsiak.telegram.bot.repository.impl;

import com.tratsiak.telegram.bot.controller.telegram.session.Session;
import com.tratsiak.telegram.bot.repository.RepositoryException;
import com.tratsiak.telegram.bot.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    private final WebClient client;

    @Autowired
    public SessionRepositoryImpl(WebClient client) {
        this.client = client;
    }

    @Override
    public List<Session> getSessions() throws RepositoryException {
        try {
            return client.get()
                    .uri(uriBuilder -> uriBuilder.path("/BotManager/employees?status=true").build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, error -> Mono.error(
                            new RuntimeException(String.valueOf(error.statusCode()))))
                    .bodyToMono(new ParameterizedTypeReference<List<Session>>() {
                    })
                    .block();
        } catch (RuntimeException e) {
            throw new RepositoryException("Failed to obtain sessions", e);
        }
    }
}
