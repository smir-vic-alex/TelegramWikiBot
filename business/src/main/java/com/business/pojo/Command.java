package com.business.pojo;

/**
 * Контейнер ответа на команду (напр. /start или /info)
 * Created by Виктор on 11.03.2018.
 */
public class Command implements Answer {
    private String answer;

    @Override
    public String getAnswer() {
        return answer;
    }

    public Command(String answer) {
        this.answer = answer;
    }
}
