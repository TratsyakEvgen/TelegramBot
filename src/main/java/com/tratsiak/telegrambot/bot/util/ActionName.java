package com.tratsiak.telegrambot.bot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ActionName {
    ACCOMI_ACTIVATE("Активация договора в АССОМИ"),
    ACCOMI_STATUS("Статус договора в АССОМИ"),
    TM_STATUS("Статус договора в АССОМИ"),
    LOCK("Блокировка");

    private final String title;

}
