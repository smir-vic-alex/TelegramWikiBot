package com.common.wiki.tgm.interfaces;

public interface Cacheble<C, K, V> {

    C getCache();

    V getCachedObj(K key);

}
