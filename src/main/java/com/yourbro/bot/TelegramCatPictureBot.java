package com.yourbro.bot;

import com.yourbro.configurationservice.ConfigurationService;
import org.springframework.beans.factory.annotation.Required;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramCatPictureBot extends TelegramLongPollingBot {
    private static final String BOT_USERNAME = "YourBro";
    private static final String BOT_TOKEN = "bot.token";
    private static final String SECURITY_PROPERTIES = "security.properties";

    private ConfigurationService configurationService;

    public void onUpdateReceived(Update update) {

    }

    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return configurationService
                .getPropertiesFile(SECURITY_PROPERTIES)
                .getProperty(BOT_TOKEN);
    }

    @Required
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
