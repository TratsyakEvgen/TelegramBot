package com.tratsiak.telegram.bot.service;

import com.tratsiak.telegram.bot.model.Contract;
import com.tratsiak.telegram.bot.model.Event;
import com.tratsiak.telegram.bot.model.InfoByActivation;
import com.tratsiak.telegram.bot.model.StatusByContract;
import com.tratsiak.telegram.bot.util.valodation.ValidationException;

public interface ContractService {
    void lock(Contract contract) throws ServiceException, ValidationException;

    void activate(Contract contract, Event event) throws ServiceException, ValidationException;

    StatusByContract getStatusFromASSOMI(Contract contract) throws ServiceException, ValidationException;

    String getStatusFromTM(Contract contract) throws ServiceException, ValidationException;

    InfoByActivation getInfoByActivation(Contract contract) throws ServiceException, ValidationException;
}
