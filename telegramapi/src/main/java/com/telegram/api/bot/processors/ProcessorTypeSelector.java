package com.telegram.api.bot.processors;

import com.business.BusinessConfig;
import com.telegram.api.bot.TelegramApiModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Селектор типа обработчика сообщения
 * Created by Виктор on 11.03.2018.
 */
public class ProcessorTypeSelector {

    private static final String STRANGE_REQUEST_TYPE = "Strange request type, see update: ";
    private static Logger LOGGER = LoggerFactory.getLogger(ProcessorTypeSelector.class);

    @Autowired
    private BusinessConfig config;

    /**
     * Выбрать тип обработчика в зависимости от содержимого сообщения
     * @param update прищедшее сообщение
     * @return тип обработчика
     */
    public Class getType(Update update) {
        if (isTextMessage(update)) {
            return getTextProcessor(update);
        } else if (update.hasCallbackQuery()) {
            return CallBackProcessor.class;
        }
        LOGGER.error(STRANGE_REQUEST_TYPE + update.toString());
        throw new TelegramApiModuleException(STRANGE_REQUEST_TYPE + update.toString());
    }

    private Class getTextProcessor(Update update) {
        String message = update.getMessage().getText();
        if (isCommandAdded(message)) {
            return CommandProcessor.class;
        }
        return WikiAnswerProcessor.class;
    }

    private boolean isCommandAdded(String message) {
        return config.getCommandsMap().keySet().contains(message.toLowerCase());
    }

    private boolean isTextMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
