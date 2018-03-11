package com.telegram.api.bot.processors;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Абстрактный класс для обработчиков, которые работают с кнопками
 * Created by Виктор on 11.03.2018.
 */
public abstract class KeyBoardMessageProcessor extends BotMessageProcessor {

    protected InlineKeyboardMarkup getKeyBoard(List<String> links) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        for (String link : links) {
            rowsInline.add(Collections.singletonList(new InlineKeyboardButton().setText(link).setCallbackData(link)));
        }
        markup.setKeyboard(rowsInline);
        return markup;
    }
}
