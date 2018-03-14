package com.telegram.api.bot;

import com.telegram.api.bot.messages.MessageWikiBotResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


/**
 * Реализация телеграм бота
 * Created by Виктор on 08.03.2018.
 */
public class TelegramBot extends TelegramLongPollingBot {

    public static final String TELEGRAM_API_EXCEPTION_MESSAGE = "TelegramApiException with update: ";
    private static Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);
    private String token;
    private String name;

    @Autowired
    private MessageWikiBotResolver resolver;

    public String getBotToken() {
        return token;
    }

    public void onUpdateReceived(Update update) {
        try {
            execute(resolver.resolve(update));
        } catch (TelegramApiException e) {
            LOGGER.error(TELEGRAM_API_EXCEPTION_MESSAGE + update.toString(), e);
        }
    }

    public String getBotUsername() {
        return name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setName(String name) {
        this.name = name;
    }
}
