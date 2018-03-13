package com.business.cache;

import com.business.pojo.Article;
import org.ehcache.Cache;

/**
 * Кэш для статей вики
 * Created by Виктор on 11.03.2018.
 */
public class ArticleCache extends BusinessCache {

    public ArticleCache(String myUrl, String cacheName) {
        super(myUrl, cacheName);
    }

    @Override
    public Cache<String, Article> getCache() {
        return cacheManager.getCache(cacheName, String.class, Article.class);
    }

    @Override
    public Article getCachedObj(String key) {
        return this.getCache().get(key);
    }
}
