package com.tratsiak.telegrambot.bot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ServiceName {
    IMS("IMS"),
    BYFLY("ByFly"),
    ZALA("Zala"),
    PACKAGE("Пакет услуг");

    private final String title;


}
