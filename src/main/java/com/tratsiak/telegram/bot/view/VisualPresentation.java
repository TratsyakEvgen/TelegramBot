package com.tratsiak.telegram.bot.view;

import com.tratsiak.telegram.bot.model.Event;
import com.tratsiak.telegram.bot.model.InfoByActivation;
import com.tratsiak.telegram.bot.model.StatusByContract;
import com.tratsiak.telegram.bot.model.StatusByService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;
import java.util.Map;


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
                    .callbackData("/menuASSOMI")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("TM")
                    .callbackData("/menuTM")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/start")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup ASSOMI = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Активация")
                    .callbackData("/activateMenuASSOMI")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Статус")
                    .callbackData("/statusMenuASSOMI")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/mainMenu")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup TM = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Статус")
                    .callbackData("/statusMenuTM")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Блокировка")
                    .callbackData("/blockMenuTM")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Назад")
                    .callbackData("/mainMenu")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup ACTIVATION_CONFIRMATION = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Да")
                    .callbackData("/activate")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Нет")
                    .callbackData("/menuASSOMI")
                    .build()))
            .build();

    public final static InlineKeyboardMarkup SELECT_SERVICE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("IMS")
                    .callbackData("/activateIMS")
                    .build()))
            .keyboardRow(List.of(InlineKeyboardButton.builder()
                    .text("Пакет промо")
                    .callbackData("/activatePromo")
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
                    .callbackData("/AssomiMenu")
                    .build()))
            .build();


    public static SendMessage getStartMenu(String text, long chatId) {
        return getSendMessage(text, START, chatId);
    }

    public static SendMessage getMainMenu(long chatId) {
        return getSendMessage("С чем работаем?", MAIN, chatId);
    }

    public static SendMessage getMenuASSOMI(long chatId) {
        return getMenuASSOMI((String) null, chatId);
    }

    public static SendMessage getMenuASSOMI(String message, long chatId) {
        String userMessage = "Выберите действие:";
        if (message != null) {
            userMessage = message + "\n" + userMessage;
        }
        return getSendMessage(userMessage, ASSOMI, chatId);
    }

    public static SendMessage getMenuASSOMI(StatusByContract statusByContract, long chatId) {
        StringBuilder builder = new StringBuilder();
        builder.append(statusByContract.getSubscriberInfo());
        builder.append("\n");
        List<StatusByService> statusByServices = statusByContract.getStatusByServices();
        statusByServices.forEach(s -> {
            builder.append(s.getDate());
            builder.append(" - ");
            builder.append(s.getService());
            builder.append(" - ");
            builder.append(s.getEvent());
            builder.append(" - ");
            builder.append(s.getState());
            builder.append("\n");
        });
        builder.append("Выберите действие:");
        return getSendMessage(builder.toString(), ASSOMI, chatId);
    }

    public static SendMessage getMenuTM(long chatId) {
        return getMenuTM(null, chatId);
    }

    public static SendMessage getMenuTM(String message, long chatId) {
        String userMessage = "Выберите действие:";
        if (message != null) {
            userMessage = message + "\n" + userMessage;
        }
        return getSendMessage(userMessage, TM, chatId);
    }

    public static SendMessage getContractMenu(String numberOfContract, long chatId) {
        return getSendMessage("Введите договор (нажмите /start для отмены):", getContractMenu(numberOfContract), chatId);
    }

    public static SendMessage getCommentMenu(long chatId) {
        return getSendMessage("Введите комментарий (нажмите /start для отмены):", null, chatId);
    }

    public static SendMessage getSelectService(String message, long chatId) {
        return getSendMessage(message + "\nАктивировать как:", SELECT_SERVICE, chatId);
    }

    public static SendMessage getActivationConfirmation(String numberOfContract,
                                                        String tariff,
                                                        String service,
                                                        long chatId) {
        return getSendMessage(
                String.format("Вы уверены, что хотите активироать услугу %s по договору %s как %s",
                        tariff, numberOfContract, service), ACTIVATION_CONFIRMATION, chatId);
    }

    public static SendMessage getEventMenu(InfoByActivation infoByActivation, long chatId) {
        return getSendMessage(infoByActivation.getSubscriberInfo() + "\nВыберите действие:",
                getEvents(infoByActivation.getEventMap()), chatId);

    }

    private static InlineKeyboardMarkup getEvents(Map<String, Event> eventMap) {
        InlineKeyboardMarkup.InlineKeyboardMarkupBuilder builder = InlineKeyboardMarkup.builder();
        eventMap.values().forEach(e -> builder.keyboardRow(List.of(InlineKeyboardButton.builder()
                .text(e.getTariff())
                .callbackData("/serviceMenu/" + e.getId())
                .build())));

        builder.keyboardRow(List.of(InlineKeyboardButton.builder()
                        .text("Главное меню")
                        .callbackData("/mainMenu")
                        .build()))
                .keyboardRow(List.of(InlineKeyboardButton.builder()
                        .text("Назад")
                        .callbackData("/AssomiMenu")
                        .build()));
        return builder.build();
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