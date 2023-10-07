package com.tratsiak.telegrambot.bot.service.impl;

import com.tratsiak.telegrambot.bot.model.ActivationData;
import com.tratsiak.telegrambot.bot.service.AccomiService;
import org.springframework.stereotype.Service;

@Service
public class AccomiServiceImpl implements AccomiService {
    @Override
    public String getStatus(String contract) {
        return "Абонент";
    }

    @Override
    public void activate(ActivationData activationData) {

    }
}
