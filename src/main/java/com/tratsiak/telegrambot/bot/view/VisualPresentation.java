package com.tratsiak.telegrambot.bot.view;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;


public class VisualPresentation {
    public final static InlineKeyboardMarkup START = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Меню")
                    .callbackData("/main")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup MAIN = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Ассоми")
                    .callbackData("/accomi")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("TM")
                    .callbackData("/tm")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/start")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup ACCOMI = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Активация")
                    .callbackData("/activateMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/main")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup TM = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Статус")
                    .callbackData("/statusMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Блокировка")
                    .callbackData("/blockMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/main")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup SELECT_SERVICE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("IMS")
                    .callbackData("/activateIMS")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Пакет промо")
                    .callbackData("/activateByFly")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("ByFly")
                    .callbackData("/activateByFly")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Пакет")
                    .callbackData("/activatePackage")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Zala")
                    .callbackData("/activateZala")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Главное меню")
                    .callbackData("/main")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/accomi")
                    .build()))
            .build();

    public static ReplyKeyboardMarkup getContractMenu(String contract) {
        ReplyKeyboardMarkup.ReplyKeyboardMarkupBuilder builder = ReplyKeyboardMarkup.builder();

        if (contract != null) {
            builder.keyboardRow(new KeyboardRow(List.of(KeyboardButton.builder().text(contract).build())))
                    .keyboardRow(new KeyboardRow(List.of(KeyboardButton.builder().text("/start").build())));
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = builder.build();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;

    }

}