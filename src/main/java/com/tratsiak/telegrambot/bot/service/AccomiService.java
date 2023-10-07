package com.tratsiak.telegrambot.bot.service;

import com.tratsiak.telegrambot.bot.model.ActivationData;

public interface AccomiService {
    String getStatus(String contract);
    void activate(ActivationData activationData);
}
