package com.common.wiki.tgm;

/**
 * Эксепшен без стектрейса
 * Created by Виктор on 26.03.2018.
 */
public class QuickException extends RuntimeException {

    public QuickException(String message) {
        super(message, null, false, false);
    }
}
