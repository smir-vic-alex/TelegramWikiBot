package com.business.cache;

import com.business.pojo.Article;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ArticleCacheTest {

    private ArticleCache cache;

    @BeforeTest
    public void init() {
        cache = new ArticleCache("/ehcache.xml", "articles");
    }

    @Test
    public void test() {
        cache.getCache().put("key1", new Article());
        assertNotNull(cache.getCache().get("key1"));
        assertNotNull(cache.getCachedObj("key1"));
    }
}
