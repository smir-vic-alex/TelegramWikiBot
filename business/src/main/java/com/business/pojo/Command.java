package com.business.pojo;

/**
 * Контейнер ответа на команду (напр. /start или /info)
 * Created by Виктор on 11.03.2018.
 */
public class Command implements Answer {
    private String text;

    @Override
    public String getText() {
        return text;
    }

    public Command(String text) {
        this.text = text;
    }
}
