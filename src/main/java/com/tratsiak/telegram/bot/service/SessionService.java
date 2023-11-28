package com.tratsiak.telegram.bot.service;

import com.tratsiak.telegram.bot.controller.telegram.session.Session;

import java.util.Map;

public interface SessionService {
    Map<Long, Session> getSessions() throws ServiceException;
}
