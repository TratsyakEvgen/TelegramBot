package com.tratsiak.telegrambot.bot.repository;

import com.tratsiak.telegrambot.bot.model.Activation;
import com.tratsiak.telegrambot.bot.model.InfoByActivation;
import com.tratsiak.telegrambot.bot.model.StatusByContract;

public interface AssomiRepository {

    void activate(Activation activation) throws RepositoryException;

    StatusByContract getStatus(String contract) throws RepositoryException;

    InfoByActivation getInfoByActivation(String numberOfContract) throws RepositoryException;

}
