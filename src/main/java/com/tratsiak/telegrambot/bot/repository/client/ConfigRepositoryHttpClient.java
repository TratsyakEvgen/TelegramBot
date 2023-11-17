package com.tratsiak.telegrambot.bot.repository.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ConfigRepositoryHttpClient {
    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newBuilder().build();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
