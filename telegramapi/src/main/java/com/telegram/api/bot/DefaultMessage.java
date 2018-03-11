package com.telegram.api.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;


/**
 * Класс дефолтных сообщений
 * Created by Виктор on 11.03.2018.
 */
public class DefaultMessage {

    public static SendMessage fail(Long chatId) {
        return new SendMessage()
                .setChatId(chatId)
                .setText("Sorry, page not found");
    }

    public static EditMessageText fail(Long chatId, Integer messageId) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText("Sorry, page not found");
    }
}
