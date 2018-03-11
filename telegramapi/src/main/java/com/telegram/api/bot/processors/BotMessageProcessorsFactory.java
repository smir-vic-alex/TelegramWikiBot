package com.telegram.api.bot.processors;

import com.common.wiki.tgm.BeanFactory;

/**
 * Фабрика обработчиков сообщений
 * Created by Виктор on 09.03.2018.
 */
public class BotMessageProcessorsFactory extends BeanFactory {

    public BotMessageProcessor getProcessor(Class processorType) {
        if (processorType == CallBackProcessor.class)
            return createBean(CallBackProcessor.class);
        else if (processorType == WikiAnswerProcessor.class)
            return createBean(WikiAnswerProcessor.class);
        else if (processorType == CommandProcessor.class)
            return createBean(CommandProcessor.class);
        else
            throw new RuntimeException();
    }

}
