package com.tratsiak.telegrambot;

import com.tratsiak.telegrambot.bot.model.Contract;
import com.tratsiak.telegrambot.bot.repository.LogRepository;
import com.tratsiak.telegrambot.bot.repository.RepositoryException;
import com.tratsiak.telegrambot.bot.util.ActionName;
import com.tratsiak.telegrambot.bot.util.ServiceName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TelegramBotApplicationTests {
    @Autowired
    LogRepository logRepository;

    @Test
    void contextLoads(){
        Contract contract = Contract.builder()
                .userId(1234)
                .numberOfContract("35332535234")
                .action(ActionName.ACCOMI_ACTIVATE.getTitle())
                .comment(ServiceName.PACKAGE.getTitle())
                .build();
        try {
            logRepository.create(contract);
        } catch (RepositoryException e) {

        }
    }

}
