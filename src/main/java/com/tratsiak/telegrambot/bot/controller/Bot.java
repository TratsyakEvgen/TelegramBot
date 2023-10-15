package com.tratsiak.telegrambot.bot.controller;

import com.tratsiak.telegrambot.bot.controller.telegram.handler.CommandHandler;
import com.tratsiak.telegrambot.bot.controller.telegram.handler.CommandHandlerException;
import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;
import com.tratsiak.telegrambot.bot.controller.telegram.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private UserSession userSession;

    private @Value("${bot.name}") String name;

    public Bot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }


    @Override
    public void onUpdateReceived(Update update) {
        final Message message;
        final long userId;
        String command;

        if (update.hasCallbackQuery()) {
            CallbackQuery query = update.getCallbackQuery();
            message = query.getMessage();
            userId = query.getFrom().getId();
            command = query.getData();
        } else {
            message = update.getMessage();
            userId = message.getFrom().getId();
            command = message.getText();
        }

        Optional<Session> optionalSession = userSession.getSession(userId);
        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            session.setChatId(message.getChatId());

            if (!update.hasCallbackQuery() && !message.isCommand()) {
                session.setMassage(command);
                command = session.getNextCommand();
            }


            String finalCommand = command;
            new Thread(() -> prepareAndSendResponse(finalCommand, session)).start();
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    private void prepareAndSendResponse(String command, Session session) {
        try {
            Optional<SendMessage> optionalSendMessage = commandHandler.execute(command, session);
            if (optionalSendMessage.isPresent()) {
                execute(optionalSendMessage.get());
                session.setPreviousCommand(command);
            }
        } catch (TelegramApiException | CommandHandlerException e) {
            e.printStackTrace();
        }
    }
}