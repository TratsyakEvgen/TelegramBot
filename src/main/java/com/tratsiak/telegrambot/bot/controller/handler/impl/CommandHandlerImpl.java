package com.tratsiak.telegrambot.bot.controller.handler.impl;


import com.tratsiak.telegrambot.bot.controller.BotController;
import com.tratsiak.telegrambot.bot.controller.handler.CommandHandler;
import com.tratsiak.telegrambot.bot.controller.handler.CommandHandlerException;
import com.tratsiak.telegrambot.bot.controller.handler.annatation.Command;
import com.tratsiak.telegrambot.bot.controller.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class CommandHandlerImpl implements CommandHandler {

    @Autowired
    private BotController controller;

    public Optional<SendMessage> execute(String commandName, Session session) throws CommandHandlerException {
        SendMessage message = null;
        Class<? extends BotController> clazz = controller.getClass();
        for (Method m : clazz.getDeclaredMethods()) {
            m.setAccessible(true);
            Command annotation = m.getDeclaredAnnotation(Command.class);
            if (annotation != null) {
                if (annotation.name().equals(commandName)) {
                    try {
                        message = (SendMessage) m.invoke(controller, session);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new CommandHandlerException("Не удалось вызвать метод " + m, e);
                    }
                }
            }
        }
        return Optional.ofNullable(message);
    }
}
