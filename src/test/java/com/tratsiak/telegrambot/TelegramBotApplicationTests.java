package com.tratsiak.telegrambot;

import com.tratsiak.telegram.bot.TelegramBotApplication;
import com.tratsiak.telegram.bot.model.Contract;
import com.tratsiak.telegram.bot.repository.LogRepository;
import com.tratsiak.telegram.bot.repository.RepositoryException;
import com.tratsiak.telegram.bot.util.ActionName;
import com.tratsiak.telegram.bot.util.ServiceName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = TelegramBotApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TelegramBotApplicationTests {
    @Autowired
    LogRepository logRepository;

    @Test
    void contextLoads() {
        Contract contract = Contract.builder()
                .userId(781198053)
                .numberOfContract("35332535234")
                .action(ActionName.ASSOMI_ACTIVATE.getTitle())
                .comment(ServiceName.PACKAGE.getTitle())
                .build();
        try {
            logRepository.create(contract);

        } catch (RepositoryException e) {
            e.printStackTrace();

        }
    }

}
