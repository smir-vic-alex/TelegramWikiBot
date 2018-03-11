package com.telegram.api.bot.messages;

import com.telegram.api.bot.DefaultMessage;
import com.telegram.api.bot.processors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

import java.io.Serializable;


/**
 * передает обработку сообщения по его типу определнному процессору
 * Created by Виктор on 09.03.2018.
 */
public class MessageWikiBotResolver {
    private static Logger LOGGER = LoggerFactory.getLogger(MessageWikiBotResolver.class);

    @Autowired
    private BotMessageProcessorsFactory processorFactory;

    public BotApiMethod<? extends Serializable> resolve(Update update) {
        try {
            Class type = ProcessorTypeSelector.getType(update);
            return processorFactory.getProcessor(type).process(update);
        } catch (Exception e) {
            LOGGER.error("Error ", e);
            return DefaultMessage.fail(update.getMessage().getChatId());
        }
    }
}
