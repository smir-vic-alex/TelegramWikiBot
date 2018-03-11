package com.wiki.api;

/**
 * Интерфейс маппера ответа json в pojo
 * Created by Виктор on 10.03.2018.
 */
public interface ResponseMapper<T> {
    T mapResponse(String json) throws RuntimeException;
}
