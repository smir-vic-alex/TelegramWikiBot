package com.business;

import com.business.pojo.Answer;

/**
 * Интерфейс бизнес обработчиков сообщений
 * Created by Виктор on 09.03.2018.
 */
public interface AnswerDelegate {
    Answer getAnswer(String articleTitleName);
}
