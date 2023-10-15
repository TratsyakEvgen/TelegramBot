package com.tratsiak.telegrambot.bot.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public abstract class AbstractHttpClient {

    protected HttpClient httpClient;
    protected ObjectMapper objectMapper;
    protected String uri;


    protected String sendGet(String path) throws RepositoryException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri + path)).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode != 200) {
                throw new RepositoryException("На запрос " + uri + path + "получен ответ с кодом " + statusCode);
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RepositoryException("Не удалось отправить запрос " + uri + path, e);
        }
    }

    protected <T> List<T> sendGet(String path, Class<T[]> clazz) throws RepositoryException {
        try {
            return Arrays.asList(objectMapper.readValue(sendGet(path), clazz));
        } catch (JsonProcessingException e) {
            throw new RepositoryException("Не удалось распознать объект" , e);
        }
    }

    protected <T> void sendPost(String path, T obj) throws RepositoryException {
        try {
            String json = objectMapper.writeValueAsString(obj);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri + path))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode != 200) {
                throw new RepositoryException("На запрос " + uri + path + " получен ответ с кодом " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            throw new RepositoryException("Не удалось отправить запрос " + uri + path, e);
        }
    }

}
