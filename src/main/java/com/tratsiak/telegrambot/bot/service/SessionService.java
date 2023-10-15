package com.tratsiak.telegrambot.bot.service;

import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;

import java.util.Map;

public interface SessionService {
    Map<Long, Session> getSessions() throws ServiceException;
}
