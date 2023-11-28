package com.tratsiak.telegram.bot.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegram.bot.model.Contract;
import com.tratsiak.telegram.bot.repository.LogRepository;
import com.tratsiak.telegram.bot.repository.RepositoryException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Repository
public class LogRepositoryImpl implements LogRepository {

    private final WebClient client;
    private final ObjectMapper objectMapper;

    public LogRepositoryImpl(WebClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }


    @Override
    public void create(Contract contract) throws RepositoryException {
        try {
            String json = objectMapper.writeValueAsString(contract);

            client.post()
                    .uri(uriBuilder -> uriBuilder.path("/BotManager/histories").build())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(json)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, error -> Mono.error(
                            new RuntimeException(String.valueOf(error.statusCode()))))
                    .bodyToMono(String.class)
                    .block();
        } catch (RuntimeException e) {
            throw new RepositoryException(String.format("Failed send history %s", contract.toString()), e);
        } catch (JsonProcessingException e) {
            throw new RepositoryException(String.format("Failed to creat json from %s", contract.toString()), e);
        }
    }
}
