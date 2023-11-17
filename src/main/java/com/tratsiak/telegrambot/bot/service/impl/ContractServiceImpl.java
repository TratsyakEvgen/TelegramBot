package com.tratsiak.telegrambot.bot.service.impl;

import com.tratsiak.telegrambot.bot.model.*;
import com.tratsiak.telegrambot.bot.repository.AssomiRepository;
import com.tratsiak.telegrambot.bot.repository.LogRepository;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.repository.TmRepository;
import com.tratsiak.telegrambot.bot.service.ContractService;
import com.tratsiak.telegrambot.bot.service.ServiceException;
import com.tratsiak.telegrambot.bot.util.valodation.ObjectValidator;
import com.tratsiak.telegrambot.bot.util.valodation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private final ObjectValidator validator;
    private final LogRepository logRepository;
    private final AssomiRepository assomiRepository;
    private final TmRepository tmRepository;
    @Autowired
    public ContractServiceImpl(ObjectValidator validator,
                               LogRepository logRepository,
                               AssomiRepository assomiRepository,
                               TmRepository tmRepository) {
        this.validator = validator;
        this.logRepository = logRepository;
        this.assomiRepository = assomiRepository;
        this.tmRepository = tmRepository;
    }

    @Override
    public void lock(Contract contract) throws ServiceException, ValidationException {
        validator.validate(contract);
        try {
            logRepository.create(contract);
        } catch (RepositoryException e) {
            throw new ServiceException(String.format("Failed create lock %s", contract.toString()), e);
        }
    }

    @Override
    public InfoByActivation getInfoByActivation(Contract contract) throws ValidationException, ServiceException {
        validator.validate(contract);
        InfoByActivation infoByActivation;
        try {
            infoByActivation = assomiRepository.getInfoByActivation(contract.getNumberOfContract());
        } catch (RepositoryException e) {
            throw new ServiceException(String.format("Failed get activation info %s", contract.toString()), e);
        }
        createLog(contract);
        return infoByActivation;
    }


    @Override
    public void activate(Contract contract, Event event) throws ServiceException, ValidationException {
        validator.validate(contract);
        try {
            assomiRepository.activate(Activation.builder()
                    .event(event)
                    .numberOfContract(contract.getNumberOfContract())
                    .service(contract.getComment())
                    .build());
        } catch (RepositoryException e) {
            throw new ServiceException(
                    String.format("Failed activation %s %s", contract.toString(), event.toString()), e);
        }
        createLog(contract);
    }

    @Override
    public StatusByContract getStatusFromASSOMI(Contract contract) throws ServiceException, ValidationException {
        validator.validate(contract);
        StatusByContract statusByContract;
        try {
            statusByContract = assomiRepository.getStatus(contract.getNumberOfContract());
        } catch (RepositoryException e) {
            throw new ServiceException(String.format("Failed get status from АССОМИ %s", contract.toString()), e);
        }

        createLog(contract);
        return statusByContract;
    }

    @Override
    public String getStatusFromTM(Contract contract) throws ServiceException, ValidationException {
        validator.validate(contract);
        String status;
        try {
            status = tmRepository.getStatus(contract.getNumberOfContract());
        } catch (RepositoryException e) {
            throw new ServiceException(String.format("Failed get status from TM %s", contract.toString()), e);
        }
        createLog(contract);
        return status;
    }

    private void createLog(Contract contract) {
        try {
            logRepository.create(contract);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}
