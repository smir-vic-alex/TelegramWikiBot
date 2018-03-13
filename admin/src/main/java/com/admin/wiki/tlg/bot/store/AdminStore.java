package com.admin.wiki.tlg.bot.store;

import com.common.wiki.tgm.cache.EhCacheBase;
import com.common.wiki.tgm.interfaces.Store;
import org.ehcache.Cache;

public abstract class AdminStore extends EhCacheBase implements Store<Cache> {

    public AdminStore(String myUrl, String cacheName) {
        super(myUrl, cacheName);
    }
}
