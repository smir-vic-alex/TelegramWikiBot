package com.common.wiki.tgm;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class EndpointBase<T> extends SpringBeanAutowiringSupport{

    private T service;

    public T getService() {
        return service;
    }

    public void setService(T service) {
        this.service = service;
    }
}
