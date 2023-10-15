package com.tratsiak.telegrambot.bot.controller.telegram;

import com.tratsiak.telegrambot.bot.controller.telegram.handler.annatation.Command;
import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;
import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.service.ContractService;
import com.tratsiak.telegrambot.bot.service.ServiceException;
import com.tratsiak.telegrambot.bot.util.ActionName;
import com.tratsiak.telegrambot.bot.util.ServiceName;
import com.tratsiak.telegrambot.bot.util.valodation.ValidationException;
import com.tratsiak.telegrambot.bot.view.VisualPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class BotController {


    @Autowired
    private ContractService contractService;


    @Command(name = "/start")
    public SendMessage getStartMenu(Session session) {
        return VisualPresentation.getStartMenu(String.format("Привет, %s!", session.getName()), session.getChatId());
    }

    @Command(name = "/mainMenu")
    public SendMessage getMainMenu(Session session) {
        return VisualPresentation.getMainMenu(session.getChatId());
    }

    @Command(name = "/accomiMenu")
    public SendMessage getAccomiMenu(Session session) {
        return VisualPresentation.getAccomiMenu(session.getChatId());
    }

    @Command(name = "/tmMenu")
    public SendMessage getTmMenu(Session session) {
        return VisualPresentation.getTmMenu(session.getChatId());
    }

    @Command(name = "/statusTmMenu")
    public SendMessage getContractStatusTm(Session session) {
        session.setNextCommand("/statusTm");
        return VisualPresentation.getContractMenu(session.getContract(), session.getChatId());
    }

    @Command(name = "/blockTmMenu")
    public SendMessage getContractBlockTm(Session session) {
        session.setNextCommand("/blockTmComment");
        return VisualPresentation.getContractMenu(session.getContract(), session.getChatId());
    }

    @Command(name = "/blockTmComment")
    public SendMessage getCommentBlockTm(Session session) {
        session.setNextCommand("/blockTm");
        session.setContract(session.getMassage());
        return VisualPresentation.getCommentMenu(session.getChatId());
    }

    @Command(name = "/activateAccomiMenu")
    public SendMessage getContractActivateAccomi(Session session) {
        session.setNextCommand("/serviceMenu");
        session.setContract(session.getMassage());
        return VisualPresentation.getContractMenu(session.getContract(), session.getChatId());
    }

    @Command(name = "/statusAccomiMenu")
    public SendMessage getContractStatusAccomi(Session session) {
        session.setNextCommand("/statusAccomi");
        return VisualPresentation.getContractMenu(session.getContract(), session.getChatId());
    }


    @Command(name = "/statusAccomi")
    public SendMessage getStatusAccomi(Session session) {
        session.setNextCommand(null);
        String numberOfContract = session.getMassage();
        session.setContract(numberOfContract);
        long chatId = session.getChatId();

        Contract contract = Contract.builder()
                .userId(session.getUserId())
                .numberOfContract(numberOfContract)
                .action(ActionName.ACCOMI_STATUS.getTitle())
                .build();

        try {
            String status = contractService.getStatusFromAccomi(contract);
            return VisualPresentation.getAccomiMenu(status, chatId);
        } catch (ServiceException e) {
            e.printStackTrace();
            return VisualPresentation.getAccomiMenu(
                    "Произошла ошибка! Не удалось получить статус", chatId);
        } catch (ValidationException e) {
            return VisualPresentation.getAccomiMenu(e.getMessage(), chatId);
        }
    }

    @Command(name = "/statusTm")
    public SendMessage getStatusTm(Session session) {
        session.setNextCommand(null);
        String numberOfContract = session.getMassage();
        session.setContract(numberOfContract);
        long chatId = session.getChatId();

        Contract contract = Contract.builder()
                .userId(session.getUserId())
                .numberOfContract(numberOfContract)
                .action(ActionName.TM_STATUS.getTitle())
                .build();

        try {
            String status = contractService.getStatusFromTM(contract);
            return VisualPresentation.getTmMenu(status, chatId);
        } catch (ServiceException e) {
            e.printStackTrace();
            return VisualPresentation.getTmMenu(
                    "Произошла ошибка! Не удалось получить статус", chatId);
        } catch (ValidationException e) {
            return VisualPresentation.getTmMenu(e.getMessage(), chatId);
        }
    }

    @Command(name = "/blockTm")
    public SendMessage blockTm(Session session) {
        if (checkPreviousCommand(session, "/blockTmComment")) {
            session.setNextCommand(null);
            long chatId = session.getChatId();

            Contract contract = Contract.builder()
                    .userId(session.getUserId())
                    .numberOfContract(session.getContract())
                    .action(ActionName.LOCK.getTitle())
                    .comment(session.getMassage())
                    .build();
            try {
                contractService.lock(contract);
                return VisualPresentation.getTmMenu("Запрос на блокировку отправлен", chatId);
            } catch (ServiceException e) {
                e.printStackTrace();
                return VisualPresentation.getTmMenu(
                        "Произошла ошибка! Не удалось отправить запрос на блокировку", chatId);
            } catch (ValidationException e) {
                return VisualPresentation.getTmMenu(e.getMessage(), chatId);
            }
        }
        return getContractBlockTm(session);
    }


    @Command(name = "/serviceMenu")
    public SendMessage getServiceMenu(Session session) {
        session.setNextCommand(null);
        String numberOfContract = session.getMassage();
        session.setContract(numberOfContract);
        long chatId = session.getChatId();

        Contract contract = Contract.builder()
                .userId(session.getUserId())
                .numberOfContract(numberOfContract)
                .action(ActionName.TM_STATUS.getTitle())
                .build();

        try {
            String status = contractService.getStatusFromAccomi(contract);
            return VisualPresentation.getSelectService(status, session.getChatId());
        } catch (ServiceException e) {
            e.printStackTrace();
            return VisualPresentation.getAccomiMenu(
                    "Произошла ошибка! Не удалось получить статус", chatId);
        } catch (ValidationException e) {
            return VisualPresentation.getAccomiMenu(e.getMessage(), chatId);
        }
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


    private SendMessage activate(Session session, ServiceName serviceName) {
        if (checkPreviousCommand(session, "/serviceMenu")) {
            session.setNextCommand(null);
            String numberOfContract = session.getMassage();
            session.setContract(numberOfContract);
            long chatId = session.getChatId();

            Contract contract = Contract.builder()
                    .userId(session.getUserId())
                    .numberOfContract(numberOfContract)
                    .action(ActionName.ACCOMI_ACTIVATE.getTitle())
                    .comment(serviceName.getTitle())
                    .build();
            try {
                contractService.activate(contract);
                return VisualPresentation.getAccomiMenu("Отправлено на активацию", chatId);
            } catch (ServiceException e) {
                e.printStackTrace();
                return VisualPresentation.getAccomiMenu(
                        "Произошла ошибка! Не удалось отправить запрос на активацию", chatId);
            } catch (ValidationException e) {
                return VisualPresentation.getAccomiMenu(e.getMessage(), chatId);
            }
        }
        return getContractActivateAccomi(session);
    }

    private boolean checkPreviousCommand(Session session, String command) {
        String previousCommand = session.getPreviousCommand();
        if (previousCommand == null) {
            return false;
        }
        return previousCommand.equals(command);
    }
}
