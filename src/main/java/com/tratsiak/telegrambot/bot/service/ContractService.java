package com.tratsiak.telegrambot.bot.service;

import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.model.Event;
import com.tratsiak.telegrambot.bot.model.InfoByActivation;
import com.tratsiak.telegrambot.bot.model.StatusByContract;
import com.tratsiak.telegrambot.bot.util.valodation.ValidationException;

public interface ContractService {
    void lock(Contract contract) throws ServiceException, ValidationException;

    void activate(Contract contract, Event event) throws ServiceException, ValidationException;

    StatusByContract getStatusFromASSOMI(Contract contract) throws ServiceException, ValidationException;

    String getStatusFromTM(Contract contract) throws ServiceException, ValidationException;

    InfoByActivation getInfoByActivation(Contract contract) throws ServiceException, ValidationException;
}
