package com.tratsiak.telegrambot.bot.controller.telegram.session;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
    @JsonSetter("telegramId")
    private long userId;
    private long chatId;
    private int messageId;
    private String name;
    private String nextCommand;
    private String previousCommand;
    private String contract;
    private String massage;
}

