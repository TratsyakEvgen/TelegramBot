package com.tratsiak.telegrambot.web.controller;

import com.tratsiak.telegrambot.bot.controller.session.UserSession;
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
            userSession.initSession();
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
