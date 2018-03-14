package com.telegram.api.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;



/**
 * Класс дефолтных сообщений
 * Created by Виктор on 11.03.2018.
 */
public class BotMessageUtils {

    private static final String PAGE_NOT_FOUND = "Sorry, page not found";

    public static SendMessage fail(Long chatId) {
        return new SendMessage()
                .setChatId(chatId)
                .setText(PAGE_NOT_FOUND);
    }

    public static EditMessageText fail(Long chatId, Integer messageId) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(PAGE_NOT_FOUND);
    }

    public static SendMessage message(Long chatId, String text, ReplyKeyboard replyMarkup) {
        return new SendMessage()
                .setChatId(chatId)
                .setText(text)
                .setReplyMarkup(replyMarkup);
    }

    public static SendMessage message(Long chatId, String text) {
        return new SendMessage()
                .setChatId(chatId)
                .setText(text);
    }

    public static EditMessageText message(Long chatId, Integer messageId, String text, InlineKeyboardMarkup replyMarkup) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(replyMarkup);
    }
}
