package com.tratsiak.telegram.bot.repository;

import com.tratsiak.telegram.bot.model.Activation;
import com.tratsiak.telegram.bot.model.InfoByActivation;
import com.tratsiak.telegram.bot.model.StatusByContract;

public interface AssomiRepository {

    void activate(Activation activation) throws RepositoryException;

    StatusByContract getStatus(String contract) throws RepositoryException;

    InfoByActivation getInfoByActivation(String numberOfContract) throws RepositoryException;

}
