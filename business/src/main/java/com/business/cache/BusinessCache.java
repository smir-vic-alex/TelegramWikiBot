package com.business.cache;

import com.common.wiki.tgm.cache.EhCacheBase;


/**
 * Базовый кэш бизнес модуля
 * Created by Виктор on 11.03.2018.
 */
public abstract class BusinessCache extends EhCacheBase {

    public BusinessCache(String myUrl, String cacheName) {
        super(myUrl, cacheName);
    }
}
