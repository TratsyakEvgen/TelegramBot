package com.tratsiak.telegrambot.bot.controller.web;

import com.tratsiak.telegrambot.bot.controller.telegram.session.UserSession;
import com.tratsiak.telegrambot.bot.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.generics.BotSession;


@RestController
@RequestMapping("/bot")
public class BotManagementController {

    @Autowired
    private BotSession botSession;
    @Autowired
    private UserSession userSession;

    @GetMapping("/start")
    private String startBot() {
        if (!botSession.isRunning()) {
            botSession.start();
            try {
                userSession.initSession();
            } catch (ServiceException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
        return "start";
    }

    @GetMapping("/stop")
    private String stopBot() {
        if (botSession.isRunning()) {
            botSession.stop();
        }
        return "stop";
    }
}
