package com.tratsiak.telegrambot.bot.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Activation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String numberOfContract;
    private String service;
    private Event event;
}
