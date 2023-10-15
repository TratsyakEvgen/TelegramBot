package com.tratsiak.telegrambot.bot.view;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;


public class VisualPresentation {
    public final static InlineKeyboardMarkup START = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Меню")
                    .callbackData("/mainMenu")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup MAIN = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Ассоми")
                    .callbackData("/accomiMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("TM")
                    .callbackData("/tmMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/start")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup ACCOMI = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Активация")
                    .callbackData("/activateAccomiMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Статус")
                    .callbackData("/statusAccomiMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/mainMenu")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup TM = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Статус")
                    .callbackData("/statusTmMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Блокировка")
                    .callbackData("/blockTmMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/mainMenu")
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
                    .callbackData("/mainMenu")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/accomiMenu")
                    .build()))
            .build();


    public static SendMessage getStartMenu(String text, long chatId) {
        return getSendMessage(text, START, chatId);
    }

    public static SendMessage getMainMenu(long chatId) {
        return getSendMessage("С чем работаем?", MAIN, chatId);
    }

    public static SendMessage getAccomiMenu(long chatId) {
        return getAccomiMenu(null, chatId);
    }

    public static SendMessage getAccomiMenu(String message, long chatId) {
        String userMessage = "Выберите действие:";
        if (message != null) {
            userMessage= message + "\n" + userMessage;
        }
        return getSendMessage(userMessage, ACCOMI, chatId);
    }

    public static SendMessage getTmMenu(long chatId) {
        return getTmMenu(null,chatId);
    }

    public static SendMessage getTmMenu(String message, long chatId) {
        String userMessage = "Выберите действие:";
        if (message != null) {
            userMessage= message + "\n" + userMessage;
        }
        return getSendMessage(userMessage, TM, chatId);
    }

    public static SendMessage getContractMenu(String contract, long chatId) {
        return getSendMessage("Введите договор (нажмите /start для отмены):", getContractMenu(contract), chatId);
    }

    public static SendMessage getCommentMenu(long chatId) {
        return getSendMessage("Введите комментарий (нажмите /start для отмены):", null, chatId);
    }

    public static SendMessage getSelectService(String messaege, long chatId) {
        return getSendMessage(messaege + "\nАктивировать как:", SELECT_SERVICE, chatId);
    }


    private static ReplyKeyboardMarkup getContractMenu(String contract) {
        ReplyKeyboardMarkup.ReplyKeyboardMarkupBuilder builder = ReplyKeyboardMarkup.builder();

        if (contract != null) {
            builder.keyboardRow(new KeyboardRow(List.of(KeyboardButton.builder().text(contract).build())));
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = builder.build();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;

    }

    private static SendMessage getSendMessage(String text, ReplyKeyboard keyboard, long chatId) {
        SendMessage.SendMessageBuilder builder = SendMessage.builder()
                .text(text)
                .chatId(chatId);
        if (keyboard != null) {
            builder.replyMarkup(keyboard);
        }
        return builder.build();
    }

}