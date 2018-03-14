package com.business.delegates;

import com.business.pojo.Answer;

/**
 * Интерфейс бизнес обработчиков сообщений
 * Created by Виктор on 09.03.2018.
 */
public interface AnswerBusinessHandler {
    Answer getAnswer(String articleTitleName);
}
