package com.common.wiki.tgm;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Базовый класс для rest endpoint
 *
 * @param <T> сервис обработчик
 */
public abstract class EndpointBase<T> extends SpringBeanAutowiringSupport {

    private T service;

    public T getService() {
        return service;
    }

    public void setService(T service) {
        this.service = service;
    }
}
