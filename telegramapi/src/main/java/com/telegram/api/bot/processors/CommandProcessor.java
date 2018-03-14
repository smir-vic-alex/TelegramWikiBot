package com.telegram.api.bot.processors;

import com.business.pojo.Answer;
import com.telegram.api.bot.BotMessageUtils;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.io.Serializable;

/**
 * Обработчик запросов по команде (напр. /start или /info)
 * Created by Виктор on 11.03.2018.
 */
public class CommandProcessor extends BotMessageProcessor {
    @Override
    public BotApiMethod<? extends Serializable> process(Update update) {
        Answer answer = businessHandler.getAnswer(update.getMessage().getText());
        return BotMessageUtils.message(update.getMessage().getChatId(), answer.getAnswer());
    }
}
