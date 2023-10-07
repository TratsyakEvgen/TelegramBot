package com.tratsiak.telegrambot.bot.model;

import com.tratsiak.telegrambot.bot.util.ServiceName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivationData {
    private String contract;
    private ServiceName name;
}
