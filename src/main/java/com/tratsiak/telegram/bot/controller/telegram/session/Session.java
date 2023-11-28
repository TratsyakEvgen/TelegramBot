package com.tratsiak.telegram.bot.controller.telegram.session;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.tratsiak.telegram.bot.model.Event;
import com.tratsiak.telegram.bot.util.ServiceName;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
    @JsonSetter("telegramId")
    private long userId;
    private long chatId;
    private String name;
    private String nextCommand;
    private String presentCommand;
    private String previousCommand;
    private String numberOfContract;
    private String massage;
    private Map<String, Event> eventMap;
    private Event event;
    private ServiceName service;

    public void clear() {
        nextCommand = null;
        eventMap = null;
        event = null;
        service = null;
    }
}

