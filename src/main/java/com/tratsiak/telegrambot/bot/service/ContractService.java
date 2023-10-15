package com.tratsiak.telegrambot.bot.service;

import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.util.valodation.ValidationException;

public interface ContractService {
    void lock(Contract contract) throws ServiceException, ValidationException;
    void activate(Contract contract) throws ServiceException, ValidationException;
    String getStatusFromAccomi(Contract contract) throws ServiceException, ValidationException;
    String getStatusFromTM(Contract contract) throws ServiceException, ValidationException;
}
