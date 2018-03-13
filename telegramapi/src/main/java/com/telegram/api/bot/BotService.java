package com.telegram.api.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.ArrayList;
import java.util.List;

/**
 * Инициализатор бота
 * Created by Виктор on 08.03.2018.
 */
public class BotService {
    private static Logger LOGGER = LoggerFactory.getLogger(BotService.class);

    @Autowired
    private TelegramBotsApi botsApi;
    private List<TelegramLongPollingBot> bots;
    private static List<TelegramLongPollingBot> registeredBots = new ArrayList<>();

    public BotService(List<TelegramLongPollingBot> bots) {
        this.bots = bots;
    }

    public void initBot() {
        if (bots != null) {
            for (TelegramLongPollingBot bot : bots) {
                register(bot);
            }
            bots = null;
        }
    }

    public synchronized void register(TelegramLongPollingBot bot) {
        try {
            botsApi.registerBot(bot);
            registeredBots.add(bot);
        } catch (Exception e) {
            LOGGER.error("Some bot did not register", e);
        }
    }

    public void register(String token, String name) {
        register(new TelegramBot(token, name));
    }

    public static List<TelegramLongPollingBot> getRegisteredBots() {
        return registeredBots;
    }
}
