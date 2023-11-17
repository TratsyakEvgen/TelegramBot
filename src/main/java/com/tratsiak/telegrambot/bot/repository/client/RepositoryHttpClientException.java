package com.tratsiak.telegrambot.bot.repository.client;

public class RepositoryHttpClientException extends Exception {
    public RepositoryHttpClientException() {
    }

    public RepositoryHttpClientException(String message) {
        super(message);
    }

    public RepositoryHttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryHttpClientException(Throwable cause) {
        super(cause);
    }

    public RepositoryHttpClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
