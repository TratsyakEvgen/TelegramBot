package com.tratsiak.telegrambot.bot.controller.web;

import com.tratsiak.telegrambot.bot.controller.telegram.session.UserSession;
import com.tratsiak.telegrambot.bot.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telegram.telegrambots.meta.generics.BotSession;


@RestController
@RequestMapping("/session")
public class BotWebController {
    private final BotSession botSession;
    private final UserSession userSession;

    @Autowired
    public BotWebController(BotSession botSession, UserSession userSession) {
        this.botSession = botSession;
        this.userSession = userSession;
    }

    @GetMapping
    private boolean status() {
        return botSession.isRunning();
    }

    @PostMapping
    private boolean start() {
        if (!botSession.isRunning()) {
            try {
                userSession.initSessions();
            } catch (ServiceException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ошибка! Бот не запущен", e);
            }
            botSession.start();
        }
        return botSession.isRunning();
    }

    @DeleteMapping
    private boolean stop() {
        if (botSession.isRunning()) {
            botSession.stop();
        }
        return botSession.isRunning();
    }

    @PutMapping
    private boolean restart() {
        if (botSession.isRunning()) {
            botSession.stop();
            botSession.start();
        }
        return botSession.isRunning();
    }
}
