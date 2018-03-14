package com.telegram.api.bot.messages;

import com.telegram.api.bot.BotMessageUtils;
import com.telegram.api.bot.processors.BotMessageProcessorsFactory;
import com.telegram.api.bot.processors.ProcessorTypeSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

import java.io.Serializable;


/**
 * Передает обработку сообщения по его типу определнному процессору
 * Created by Виктор on 09.03.2018.
 */
public class MessageWikiBotResolver {
    private static Logger LOGGER = LoggerFactory.getLogger(MessageWikiBotResolver.class);

    @Autowired
    private BotMessageProcessorsFactory processorFactory;
    @Autowired
    private ProcessorTypeSelector selector;

    public BotApiMethod<? extends Serializable> resolve(Update update) {
        try {
            Class type = selector.getType(update);
            return processorFactory.getProcessor(type).process(update);
        } catch (Throwable e) {
            LOGGER.error("Error handle update " + update.toString(), e);
            return BotMessageUtils.fail(update.getMessage().getChatId());
        }
    }
}
