package com.business;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;



/**
 * Базовый кэш бизнес модуля
 * Created by Виктор on 11.03.2018.
 */
public abstract class BusinessCache {

    protected CacheManager cacheManager;
    protected String cacheName;

    public BusinessCache(String myUrl, String cacheName) {
        this.cacheName = cacheName;
        cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(getClass().getResource(myUrl)));
        cacheManager.init();
    }

    protected abstract Cache getCache();

    public abstract Object get(String key);
}
