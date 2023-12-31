package com.tratsiak.telegram.bot.controller.telegram.handler.impl;


import com.tratsiak.telegram.bot.controller.telegram.BotController;
import com.tratsiak.telegram.bot.controller.telegram.handler.CommandHandler;
import com.tratsiak.telegram.bot.controller.telegram.handler.CommandHandlerException;
import com.tratsiak.telegram.bot.controller.telegram.handler.annatation.Command;
import com.tratsiak.telegram.bot.controller.telegram.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class CommandHandlerImpl implements CommandHandler {
    private final BotController controller;

    @Autowired
    public CommandHandlerImpl(BotController controller) {
        this.controller = controller;
    }

    public Optional<SendMessage> execute(String commandName, Session session) throws CommandHandlerException {
        SendMessage message = null;
        Class<? extends BotController> clazz = controller.getClass();
        for (Method m : clazz.getDeclaredMethods()) {
            m.setAccessible(true);
            Command annotation = m.getDeclaredAnnotation(Command.class);
            if (annotation != null) {
                if (commandName.contains(annotation.name())) {
                    try {
                        message = (SendMessage) m.invoke(controller, session);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new CommandHandlerException(String.format("Failed to send method %s", m.toString()), e);
                    }
                }
            }
        }
        return Optional.ofNullable(message);
    }
}
