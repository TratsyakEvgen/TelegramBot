package com.tratsiak.telegrambot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tratsiak.telegrambot.bot.controller.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.net.http.HttpClient;

@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
    }

    @Bean
    public BotSession getSession(Bot bot) throws TelegramApiException {
        BotSession session = new TelegramBotsApi(DefaultBotSession.class).registerBot(bot);
        session.stop();
        return session;
    }

    @Bean
    public HttpClient getClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }


}
