package com.tratsiak.telegram.bot.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegram.bot.model.Activation;
import com.tratsiak.telegram.bot.model.InfoByActivation;
import com.tratsiak.telegram.bot.model.StatusByContract;
import com.tratsiak.telegram.bot.repository.AssomiRepository;
import com.tratsiak.telegram.bot.repository.RepositoryException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class AssomiRepositoryImpl implements AssomiRepository {

    private final WebClient client;
    private final ObjectMapper objectMapper;

    public AssomiRepositoryImpl(WebClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }


    @Override
    public void activate(Activation activation) throws RepositoryException {

        try {
            String json = objectMapper.writeValueAsString(activation);

            client.post()
                    .uri(uriBuilder -> uriBuilder.path("/AssomiParser/assomi/activation").build())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(json)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, error -> Mono.error(
                            new RuntimeException(String.valueOf(error.statusCode()))))
                    .bodyToMono(String.class)
                    .block();
        } catch (RuntimeException e) {
            throw new RepositoryException(String.format("Failed activation %s", activation.toString()), e);
        } catch (JsonProcessingException e) {
            throw new RepositoryException(String.format("Failed to creat json from %s", activation.toString()), e);
        }
    }


    @Override
    public StatusByContract getStatus(String numberOfContract) throws RepositoryException {
        try {
            return client.get()
                    .uri(uriBuilder -> uriBuilder.path("/AssomiParser/assomi/status/" + numberOfContract).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, error -> Mono.error(
                            new RuntimeException(String.valueOf(error.statusCode()))))
                    .bodyToMono(StatusByContract.class)
                    .block();
        } catch (RuntimeException e) {
            throw new RepositoryException(String.format("Failed to obtain status contract %s", numberOfContract), e);
        }
    }

    @Override
    public InfoByActivation getInfoByActivation(String numberOfContract) throws RepositoryException {
        try {
            return client.get()
                    .uri(uriBuilder -> uriBuilder.path("/AssomiParser/assomi/activation/" + numberOfContract).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, error -> Mono.error(
                            new RuntimeException(String.valueOf(error.statusCode()))))
                    .bodyToMono(InfoByActivation.class)
                    .block();
        } catch (RuntimeException e) {
            throw new RepositoryException(String.format("Failed to obtain activation info contract %s", numberOfContract), e);
        }
    }
}
