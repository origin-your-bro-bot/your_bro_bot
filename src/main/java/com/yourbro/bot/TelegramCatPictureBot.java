package com.yourbro.bot;

import com.yourbro.cat.CatService;
import com.yourbro.configurationservice.ConfigurationService;
import org.springframework.beans.factory.annotation.Required;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;


public class TelegramCatPictureBot extends TelegramLongPollingBot {
    private static final String BOT_USERNAME = "YourBro";
    private static final String BOT_TOKEN = "bot.token";
    private static final String SECURITY_PROPERTIES = "security.properties";

    private ConfigurationService configurationService;
    private CatService catService;

    @Override
    public void onUpdateReceived(Update update) {

            if (update.hasMessage() && update.getMessage().isCommand()) {
                 String textCommand = update.getMessage().getText();
                try {
                    performCommand(update, textCommand);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                long chatId = update.getMessage().getChatId();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("/cat - get a cute cat picture");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

    }

    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return configurationService
                .getPropertiesFile(SECURITY_PROPERTIES)
                .getProperty(BOT_TOKEN);
    }

    private void performCommand(Update update, String textCommand) throws TelegramApiException {
        long chatId = update.getMessage().getChatId();

        switch (textCommand) {
            case "/cat" : {
                InputStream catInputStream = null;
                try {
                    catInputStream = catService.getCuteCatPhotoAsStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SendPhoto catPhoto = new SendPhoto();
                catPhoto.setChatId(chatId);
                catPhoto.setNewPhoto("cuteCatPhoto", catInputStream);
                sendPhoto(catPhoto);
            } break;

            default: {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("/cat - get a cute cat picture");
                execute(sendMessage);
            }

        }
    }

    @Required
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Required
    public void setCatService(CatService catService) {
        this.catService = catService;
    }
}
