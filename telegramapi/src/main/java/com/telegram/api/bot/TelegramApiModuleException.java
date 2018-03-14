package com.telegram.api.bot;

/**
 * Общее исключение для телеграм модуля
 * Created by Виктор on 15.03.2018.
 */
public class TelegramApiModuleException extends RuntimeException {

    public TelegramApiModuleException(String message) {
        super(message);
    }

    public TelegramApiModuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
