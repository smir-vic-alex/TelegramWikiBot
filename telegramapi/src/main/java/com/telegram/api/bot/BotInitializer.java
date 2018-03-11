package com.telegram.api.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.List;

/**
 * Инициализатор бота
 * Created by Виктор on 08.03.2018.
 */
public class BotInitializer {

    @Autowired
    private TelegramBotsApi botsApi;
    private List<TelegramLongPollingBot> bots;

    public BotInitializer(List<TelegramLongPollingBot> bots) {
        this.bots = bots;
    }

    public void init() {
        try {
            if(bots!=null) {
                for (TelegramLongPollingBot bot:bots) {
                    botsApi.registerBot(bot);
                }
            }
        } catch (TelegramApiRequestException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
