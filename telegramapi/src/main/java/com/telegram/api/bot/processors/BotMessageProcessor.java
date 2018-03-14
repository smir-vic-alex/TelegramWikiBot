package com.telegram.api.bot.processors;

import com.business.delegates.AnswerBusinessHandler;
import com.common.wiki.tgm.interfaces.Processor;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

import java.io.Serializable;

/**
 * Абстрактный класс для всех обработчиков бота
 * Created by Виктор on 11.03.2018.
 */
public abstract class BotMessageProcessor implements Processor<Update, BotApiMethod<? extends Serializable>> {

    protected AnswerBusinessHandler businessHandler;

    /**
     * Установить бизнес обработчик сообщения
     * @param businessHandler бизнес обработчик
     */
    public void setDelegate(AnswerBusinessHandler businessHandler) {
        this.businessHandler = businessHandler;
    }

    /**
     * Обработать сообщение
     * @param update сообщение
     * @return method Telegram Bots Api
     */
    public abstract BotApiMethod<? extends Serializable> process(Update update);
}
