package com.telegram.api.bot.processors;

import com.business.pojo.Answer;
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
        Answer answer = delegate.getAnswer(update.getMessage().getText());
        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(answer.getAnswer());
    }
}
