package com.tratsiak.telegrambot.bot.controller.telegram;

import com.tratsiak.telegrambot.bot.controller.telegram.handler.annatation.Command;
import com.tratsiak.telegrambot.bot.controller.telegram.session.Session;
import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.model.Event;
import com.tratsiak.telegrambot.bot.model.InfoByActivation;
import com.tratsiak.telegrambot.bot.model.StatusByContract;
import com.tratsiak.telegrambot.bot.service.ContractService;
import com.tratsiak.telegrambot.bot.service.ServiceException;
import com.tratsiak.telegrambot.bot.util.ActionName;
import com.tratsiak.telegrambot.bot.util.ServiceName;
import com.tratsiak.telegrambot.bot.util.valodation.ValidationException;
import com.tratsiak.telegrambot.bot.view.VisualPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

@Component
public class BotController {
    private final ContractService contractService;

    @Autowired
    public BotController(ContractService contractService) {
        this.contractService = contractService;
    }


    @Command(name = "/start")
    public SendMessage getStartMenu(Session session) {
        return VisualPresentation.getStartMenu(String.format("Привет, %s!", session.getName()), session.getChatId());
    }

    @Command(name = "/mainMenu")
    public SendMessage getMainMenu(Session session) {
        return VisualPresentation.getMainMenu(session.getChatId());
    }

    @Command(name = "/menuASSOMI")
    public SendMessage getMenuASSOMI(Session session) {
        return VisualPresentation.getMenuASSOMI(session.getChatId());
    }

    @Command(name = "/menuTM")
    public SendMessage getTmMenu(Session session) {
        return VisualPresentation.getMenuTM(session.getChatId());
    }

    @Command(name = "/statusMenuTM")
    public SendMessage getContractStatusTm(Session session) {
        session.setNextCommand("/statusTM");
        return VisualPresentation.getContractMenu(session.getNumberOfContract(), session.getChatId());
    }

    @Command(name = "/blockMenuTM")
    public SendMessage getContractBlockTm(Session session) {
        session.setNextCommand("/blockCommentTM");
        return VisualPresentation.getContractMenu(session.getNumberOfContract(), session.getChatId());
    }

    @Command(name = "/blockCommentTM")
    public SendMessage getCommentBlockTm(Session session) {
        session.setNextCommand("/blockTM");
        session.setNumberOfContract(session.getMassage());
        return VisualPresentation.getCommentMenu(session.getChatId());
    }

    @Command(name = "/activateMenuASSOMI")
    public SendMessage getContractActivateASSOMI(Session session) {
        session.setNextCommand("/eventMenuASSOMI");
        return VisualPresentation.getContractMenu(session.getNumberOfContract(), session.getChatId());
    }

    @Command(name = "/statusMenuASSOMI")
    public SendMessage getContractStatusASSOMI(Session session) {
        session.setNextCommand("/statusASSOMI");
        return VisualPresentation.getContractMenu(session.getNumberOfContract(), session.getChatId());
    }


    @Command(name = "/statusASSOMI")
    public SendMessage getStatusASSOMI(Session session) {
        session.setNextCommand(null);
        String numberOfContract = session.getMassage();
        session.setNumberOfContract(numberOfContract);
        long chatId = session.getChatId();

        Contract contract = Contract.builder()
                .userId(session.getUserId())
                .numberOfContract(numberOfContract)
                .action(ActionName.ASSOMI_STATUS.getTitle())
                .build();

        try {
            StatusByContract statusByContract = contractService.getStatusFromASSOMI(contract);
            if (statusByContract.getSubscriberInfo() == null) {
                return VisualPresentation.getMenuASSOMI(
                        String.format("По договору %s ничего не найдено", numberOfContract), chatId);
            }
            return VisualPresentation.getMenuASSOMI(statusByContract, chatId);
        } catch (ServiceException e) {
            e.printStackTrace();
            return VisualPresentation.getMenuASSOMI(
                    "Произошла ошибка! Не удалось получить статус из Ассоми", chatId);
        } catch (ValidationException e) {
            return VisualPresentation.getMenuASSOMI(e.getMessage(), chatId);
        }
    }

    @Command(name = "/statusTM")
    public SendMessage getStatusTm(Session session) {
        session.setNextCommand(null);
        String numberOfContract = session.getMassage();
        session.setNumberOfContract(numberOfContract);
        long chatId = session.getChatId();

        Contract contract = Contract.builder()
                .userId(session.getUserId())
                .numberOfContract(numberOfContract)
                .action(ActionName.TM_STATUS.getTitle())
                .build();

        try {
            String status = contractService.getStatusFromTM(contract);
            return VisualPresentation.getMenuTM(status, chatId);
        } catch (ServiceException e) {
            e.printStackTrace();
            return VisualPresentation.getMenuTM(
                    "Произошла ошибка! Не удалось получить статус из TM", chatId);
        } catch (ValidationException e) {
            return VisualPresentation.getMenuTM(e.getMessage(), chatId);
        }
    }

    @Command(name = "/blockTM")
    public SendMessage blockTm(Session session) {
        if (checkPreviousCommand(session, "/blockCommentTM")) {
            session.setNextCommand(null);
            long chatId = session.getChatId();

            Contract contract = Contract.builder()
                    .userId(session.getUserId())
                    .numberOfContract(session.getNumberOfContract())
                    .action(ActionName.LOCK.getTitle())
                    .comment(session.getMassage())
                    .build();
            try {
                contractService.lock(contract);
                return VisualPresentation.getMenuTM("Запрос на блокировку отправлен", chatId);
            } catch (ServiceException e) {
                e.printStackTrace();
                return VisualPresentation.getMenuTM(
                        "Произошла ошибка! Не удалось отправить запрос на блокировку", chatId);
            } catch (ValidationException e) {
                return VisualPresentation.getMenuTM(e.getMessage(), chatId);
            }
        }
        return getContractBlockTm(session);
    }


    @Command(name = "/eventMenuASSOMI")
    public SendMessage getEventMenu(Session session) {
        session.setNextCommand(null);
        String numberOfContract = session.getMassage();
        session.setNumberOfContract(numberOfContract);
        long chatId = session.getChatId();

        Contract contract = Contract.builder()
                .userId(session.getUserId())
                .numberOfContract(numberOfContract)
                .action(ActionName.ASSOMI_INFO_BY_ACTIVATION.getTitle())
                .build();

        try {
            InfoByActivation infoByActivation = contractService.getInfoByActivation(contract);
            if (infoByActivation.getSubscriberInfo() == null) {
                return VisualPresentation.getMenuASSOMI(
                        String.format("По договору %s ничего не найдено", numberOfContract), chatId);
            }
            Map<String, Event> eventMap = infoByActivation.getEventMap();
            if (eventMap.isEmpty()) {
                return VisualPresentation.getMenuASSOMI(
                        String.format("По договору %s все услуги активированы", numberOfContract), chatId);
            }
            session.setEventMap(eventMap);
            return VisualPresentation.getEventMenu(infoByActivation, session.getChatId());
        } catch (ServiceException e) {
            e.printStackTrace();
            return VisualPresentation.getMenuASSOMI(
                    "Произошла ошибка! Не удалось получить информацию об неактивированных услугах", chatId);
        } catch (ValidationException e) {
            return VisualPresentation.getMenuASSOMI(e.getMessage(), chatId);
        }
    }


    @Command(name = "/serviceMenu")
    public SendMessage getServiceMenu(Session session) {
        long chatId = session.getChatId();

        Map<String, Event> eventMap = session.getEventMap();

        if (eventMap == null) {
            return VisualPresentation.getMenuASSOMI(chatId);
        }

        String eventId = session.getPresentCommand().split("/")[2];
        Event event = eventMap.get(eventId);
        session.setEventMap(null);

        if (event == null) {
            return VisualPresentation.getMenuASSOMI(chatId);
        }
        session.setEvent(event);

        return VisualPresentation.getSelectService(event.getTariff(), session.getChatId());
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
        long chatId = session.getChatId();

        Event event = session.getEvent();
        session.setEvent(null);
        if (event == null) {
            return VisualPresentation.getMenuASSOMI(chatId);
        }

        Contract contract = Contract.builder()
                .userId(session.getUserId())
                .numberOfContract(session.getNumberOfContract())
                .action(ActionName.ASSOMI_ACTIVATE.getTitle())
                .comment(serviceName.getTitle())
                .build();
        try {
            contractService.activate(contract, event);
            return VisualPresentation.getMenuASSOMI("Отправлено на активацию", chatId);
        } catch (ServiceException e) {
            e.printStackTrace();
            return VisualPresentation.getMenuASSOMI(
                    "Произошла ошибка! Не удалось отправить запрос на активацию", chatId);
        } catch (ValidationException e) {
            return VisualPresentation.getMenuASSOMI(e.getMessage(), chatId);
        }
    }

    private boolean checkPreviousCommand(Session session, String command) {
        String previousCommand = session.getPreviousCommand();
        if (previousCommand == null) {
            return false;
        }
        return previousCommand.equals(command);
    }
}
