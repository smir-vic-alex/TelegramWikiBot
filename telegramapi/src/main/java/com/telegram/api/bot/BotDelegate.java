package com.telegram.api.bot;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Виктор on 14.03.2018.
 */
public class BotDelegate {

    @Autowired
    private BotService botService;

    public void registerNewBot(String token, String name) {
        botService.register(token, name);
    }
}
