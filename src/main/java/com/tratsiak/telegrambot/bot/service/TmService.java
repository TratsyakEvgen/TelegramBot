package com.tratsiak.telegrambot.bot.service;

public interface TmService {
    String getStatus(String contract);
    void block(String contract);
}
