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
public class StatusByService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String service;
    private String date;
    private String event;
    private String state;
}
