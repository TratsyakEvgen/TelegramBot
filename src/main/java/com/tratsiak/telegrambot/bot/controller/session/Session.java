package com.tratsiak.telegrambot.bot.controller.session;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Session {
    private long userId;
    private long chatId;
    private int messageId;
    private String name;
    private String nextCommand;
    private String contract;
}

