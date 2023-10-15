package com.tratsiak.telegrambot.bot.service.impl;

import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.repository.*;
import com.tratsiak.telegrambot.bot.service.ContractService;
import com.tratsiak.telegrambot.bot.service.ServiceException;
import com.tratsiak.telegrambot.bot.util.valodation.ObjectValidator;
import com.tratsiak.telegrambot.bot.util.valodation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ObjectValidator validator;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private AccomiRepository accomiRepository;

    @Autowired
    private TmRepository tmRepository;

    @Override
    public void lock(Contract contract) throws ServiceException, ValidationException {
        validator.validate(contract);
        try {
            logRepository.create(contract);
        } catch (RepositoryException e) {
            throw new ServiceException("Ошибка при создании блокировки " + contract.toString(), e);
        }
    }

    @Override
    public void activate(Contract contract) throws ServiceException, ValidationException {
        validator.validate(contract);
        try {
            accomiRepository.activate(contract);
        } catch (RepositoryException e) {
            throw new ServiceException("Ошибка при активации " + contract.toString(), e);
        }
        createLog(contract);
    }

    @Override
    public String getStatusFromAccomi(Contract contract) throws ServiceException, ValidationException {
        validator.validate(contract);
        String status;
        try {
            status =  accomiRepository.getStatus(contract.getNumberOfContract());
        } catch (RepositoryException e) {
            throw new ServiceException("Ошибка получения статуса АССОМИ " + contract.toString(), e);
        }
        createLog(contract);
        return status;
    }

    @Override
    public String getStatusFromTM(Contract contract) throws ServiceException, ValidationException {
        validator.validate(contract);
        String status;
        try {
            status = tmRepository.getStatus(contract.getNumberOfContract());
        } catch (RepositoryException e) {
            throw new ServiceException("Ошибка получения статуса ТМ " + contract.toString(), e);
        }
        createLog(contract);
        return status;
    }

    private void createLog(Contract contract){
        try {
            logRepository.create(contract);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}
