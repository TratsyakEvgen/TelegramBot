package com.tratsiak.telegrambot.bot.repository.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClient;
import com.tratsiak.telegrambot.bot.repository.client.RepositoryHttpClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Component
public class RepositoryHttpClientImpl implements RepositoryHttpClient {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public RepositoryHttpClientImpl(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public String getString(String uri) throws RepositoryHttpClientException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).GET().build();
        return send(request).body();
    }


    public <T> void post(String uri, T obj) throws RepositoryHttpClientException {
        post(uri, obj, 3L);
    }


    public <T> void post(String uri, T obj, long timeout) throws RepositoryHttpClientException {
        String json;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RepositoryHttpClientException(String.format("Failed to creat json from %s", obj.toString()));
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(timeout))
                .build();

        send(request);
    }



    public <T> T get(String uri, Class<T> clazz) throws RepositoryHttpClientException {
        try {
            return objectMapper.readValue(getString(uri), clazz);
        } catch (JsonProcessingException e) {
            throw new RepositoryHttpClientException(
                    String.format("%s Failed to recognize object %s", uri, clazz), e);
        }
    }

    public <T> List<T> getList(String uri, Class<T[]> clazz) throws RepositoryHttpClientException {
        try {
            return Arrays.asList(objectMapper.readValue(getString(uri), clazz));
        } catch (JsonProcessingException e) {
            throw new RepositoryHttpClientException(
                    String.format("%s Failed to recognize list objects %s", uri, clazz), e);
        }
    }


    private HttpResponse<String> send(HttpRequest request) throws RepositoryHttpClientException {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode != 200) {
                throw new RepositoryHttpClientException(
                        String.format("Request %s received a response with status %s",
                                request.uri().toString(), statusCode));
            }
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RepositoryHttpClientException(
                    String.format("Failed to send request to %s", request.uri().toString()), e);
        }
    }


}
