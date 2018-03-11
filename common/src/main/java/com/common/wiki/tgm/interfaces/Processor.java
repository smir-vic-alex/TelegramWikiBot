package com.common.wiki.tgm.interfaces;

/**
 * Интерфейс процессора
 * Created by Виктор on 11.03.2018.
 */
public interface Processor<ToProcessType, ProcessedType> {

    ProcessedType process(ToProcessType toProcessObj);
}
