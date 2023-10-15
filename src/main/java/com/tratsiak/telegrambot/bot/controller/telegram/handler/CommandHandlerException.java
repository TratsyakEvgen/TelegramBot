package com.tratsiak.telegrambot.bot.controller.telegram.handler;

public class CommandHandlerException extends Exception {
    public CommandHandlerException() {
    }

    public CommandHandlerException(String message) {
        super(message);
    }

    public CommandHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandHandlerException(Throwable cause) {
        super(cause);
    }

    public CommandHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
