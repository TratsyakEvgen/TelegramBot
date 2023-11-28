package com.tratsiak.telegram.bot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ActionName {
    ASSOMI_ACTIVATE("Активация договора в АССОМИ"),
    ASSOMI_INFO_BY_ACTIVATION("Просмотр не активированных услуг в АССОМИ"),
    ASSOMI_STATUS("Статус договора в АССОМИ"),
    TM_STATUS("Статус договора в ТМ"),
    LOCK("Блокировка");

    private final String title;

}
