package com.tratsiak.telegrambot.bot.controller;

import com.tratsiak.telegrambot.bot.controller.session.Session;
import com.tratsiak.telegrambot.bot.controller.handler.annatation.Command;
import com.tratsiak.telegrambot.bot.model.ActivationData;
import com.tratsiak.telegrambot.bot.service.AccomiService;
import com.tratsiak.telegrambot.bot.util.ServiceName;
import com.tratsiak.telegrambot.bot.view.VisualPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
public class BotController {

    @Autowired
    AccomiService accomiService;

    @Command(name = "/start")
    public SendMessage getStartMenu(Session session) {
        return getSendMessage(String.format("Привет, %s!", session.getName()),
                VisualPresentation.START, session.getChatId());
    }

    @Command(name = "/main")
    public SendMessage getMainMenu(Session session) {
        return getSendMessage("С чем работаем?", VisualPresentation.MAIN, session.getChatId());
    }

    @Command(name = "/accomi")
    public SendMessage getAccomiMenu(Session session) {
        return getSendMessage("Выберите действие:", VisualPresentation.ACCOMI, session.getChatId());
    }

    @Command(name = "/tm")
    public SendMessage getTmMenu(Session session) {
        return getSendMessage("Выберите действие:", VisualPresentation.TM, session.getChatId());
    }

    @Command(name = "/statusMenu")
    public SendMessage getContractStatus(Session session) {
        session.setNextCommand("/status");
        return getSendMessage("Введите договор (нажмите /start для отмены):",
                VisualPresentation.getContractMenu(session.getContract()), session.getChatId());
    }

    @Command(name = "/blockMenu")
    public SendMessage getContractBlock(Session session) {
        session.setNextCommand("/block");
        return getSendMessage("Введите договор (нажмите /start для отмены):",
                VisualPresentation.getContractMenu(session.getContract()), session.getChatId());
    }

    @Command(name = "/activateMenu")
    public SendMessage getContract(Session session) {
        session.setNextCommand("/selectService");
        return getSendMessage("Введите договор (нажмите /start для отмены):",
                VisualPresentation.getContractMenu(session.getContract()), session.getChatId());
    }

    @Command(name = "/selectService")
    public SendMessage getServices(Session session) {
        String info = accomiService.getStatus(session.getContract());
        return getSendMessage(info + "Активировать как:",
                VisualPresentation.SELECT_SERVICE, session.getChatId());
    }

    @Command(name = "/activateIMS")
    public SendMessage activateIMS(Session session) {
        return activate(session, ServiceName.IMS);
    }

    @Command(name = "/activateByFly")
    public SendMessage activateByFly(Session session) {
        return activate(session, ServiceName.BYFLY);
    }

    @Command(name = "/activatePackage")
    public SendMessage activatePackage(Session session) {
       return activate(session, ServiceName.PACKAGE);
    }

    @Command(name = "/activateZala")
    public SendMessage activateZala(Session session) {
        return activate(session, ServiceName.ZALA);
    }



    private SendMessage activate(Session session, ServiceName serviceName){
        String contract = session.getContract();
        accomiService.activate(ActivationData.builder()
                .name(serviceName)
                .contract(session.getContract())
                .build());
        return getSendMessage("Отправлено на активацию", VisualPresentation.MAIN, session.getChatId());
    }

    private SendMessage getSendMessage(String text, ReplyKeyboard keyboard, long chatId) {
        return SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .replyMarkup(keyboard)
                .build();
    }
}
