package com.yourbro.entrypoint;

import com.yourbro.bot.TelegramCatPictureBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private static final String APPLICATION_CONTEXT_XML = "application-context.xml";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
        LOG.info("Application context successfully started. Start up date: ", context.getStartupDate());

        TelegramCatPictureBot catPictureBot = context.getBean(TelegramCatPictureBot.class);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(catPictureBot);
            LOG.info("Bot successfully registered");
        } catch (TelegramApiException e) {
            LOG.error("Bot registration is failed {}", e.getMessage());
        }
    }
}
