package com.telegram.api.bot.processors;

import com.business.BusinessConfig;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Селектор типа обработчика сообщения
 * Created by Виктор on 11.03.2018.
 */
public class ProcessorTypeSelector {

    public static Class getType(Update update) {
        if (isTextMessage(update)) {
            String message = update.getMessage().getText();
            if (BusinessConfig.getInputCommands().contains(message.toLowerCase()))
                return CommandProcessor.class;
            return WikiAnswerProcessor.class;
        } else if (update.hasCallbackQuery())
            return CallBackProcessor.class;
        throw new RuntimeException();
    }

    private static boolean isTextMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
