package com.common.wiki.tgm;

import com.common.wiki.tgm.interfaces.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Фабрика spring бинов
 * Created by Виктор on 09.03.2018.
 */
public class BeanFactory implements Factory {

    @Autowired
    private ApplicationContext applicationContext;

    public <T> T createBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}
