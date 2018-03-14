package com.telegram.api.bot;

import com.common.wiki.tgm.BeanFactory;

public class TelegramBotFactory extends BeanFactory {

    public TelegramBot getNewBot() {
        return createBean(TelegramBot.class);
    }
}
