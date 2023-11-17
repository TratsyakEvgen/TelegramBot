package com.tratsiak.telegrambot.bot.repository.client;

import java.util.List;

public interface RepositoryHttpClient {
    String getString(String path) throws RepositoryHttpClientException;

    <T> T get(String path, Class<T> clazz) throws RepositoryHttpClientException;

    <T> List<T> getList(String path, Class<T[]> clazz) throws RepositoryHttpClientException;

    <T> void post(String path, T obj) throws RepositoryHttpClientException;

    <T> void post(String path, T obj, long timeout) throws RepositoryHttpClientException;

}
