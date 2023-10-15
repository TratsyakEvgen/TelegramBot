package com.tratsiak.telegrambot.bot.controller.telegram.handler;


import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;

public interface CommandHandler {
    Optional<SendMessage> execute(String commandName, Session session) throws CommandHandlerException;
}
