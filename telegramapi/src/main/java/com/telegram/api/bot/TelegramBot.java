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
    private static Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);
    private String token;
    private String name;

    @Autowired
    private MessageWikiBotResolver resolver;

    public TelegramBot(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getBotToken() {
        return token;
    }

    public void onUpdateReceived(Update update) {
        try {
            execute(resolver.resolve(update));
        } catch (TelegramApiException e) {
            LOGGER.error("TelegramApiException ", e);
        }
    }

    public String getBotUsername() {
        return name;
    }
}
