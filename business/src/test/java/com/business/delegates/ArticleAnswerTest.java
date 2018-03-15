package com.business.delegates;

import com.business.cache.ArticleCache;
import com.business.cache.BusinessCache;
import com.business.pojo.Article;
import com.wiki.api.WikiApiConfig;
import com.wiki.api.services.WikiApiService;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.core.Ehcache;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.json.schema2pojo.dto.PageByPageIdRs;
import ru.json.schema2pojo.dto.Query_;
import ru.json.schema2pojo.dto.Search;
import ru.json.schema2pojo.dto.SearchByTitleRs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class ArticleAnswerTest {

    @Mock
    private WikiApiService wikiApiService;

    @Mock
    private ArticleCache cache;

    @Mock
    private SeeAlsoCollector seeAlsoCollector;

    @InjectMocks
    private ArticleAnswer articleAnswer;

    @BeforeTest
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        checkWithCachedValue();
        checkWithPageNotFoundInWiki();
        allSteps();
    }

    private void checkWithPageNotFoundInWiki() {
        String wikiTitleName = "wikiTitleName";
        when(cache.getCachedObj(anyString())).thenReturn(null);
        SearchByTitleRs searchByTitleRs = getSearchByTitleRs();
        searchByTitleRs.setQuery(null);
        when(wikiApiService.getPageIdByTitle(wikiTitleName)).thenReturn(searchByTitleRs);
        assertNull(articleAnswer.getAnswer(wikiTitleName));
    }

    private void checkWithCachedValue() {
        Article article = getArticle();
        when(cache.getCachedObj(anyString())).thenReturn(article);
        assertEquals(articleAnswer.getAnswer("wikiTitleName").getText(), "text");
    }

    private void allSteps() {
        String wikiTitleName = "wikiTitleName";
        SearchByTitleRs searchByTitleRs = getSearchByTitleRs();
        when(wikiApiService.getPageIdByTitle(wikiTitleName)).thenReturn(searchByTitleRs);
        Article article;PageByPageIdRs pageByPageIdRs = getPageByPageIdRs();
        when(wikiApiService.getPageContentById(searchByTitleRs.getQuery().getSearch().get(0).getPageid())).thenReturn(pageByPageIdRs);
        when(seeAlsoCollector.getSeeAlsoLinks(any(Integer.class))).thenReturn(Collections.emptyList());
        when(cache.getCache()).thenReturn(mock(Ehcache.class));
        article = articleAnswer.getAnswer(wikiTitleName);
        assertNotNull(article);
        assertEquals("TEST\nhost:port/example", article.getText());
        assertNotNull(article.getSeeAlsoLinks());
    }

    private Article getArticle() {
        Article article = new Article();
        article.setText("text");
        article.setSeeAlsoLinks(Collections.emptyList());
        return article;
    }

    private PageByPageIdRs getPageByPageIdRs() {
        PageByPageIdRs pageByPageIdRs = new PageByPageIdRs();
        pageByPageIdRs.setExtract("<html><head></head><body>TEST</body></html>");
        pageByPageIdRs.setFullurl("host:port/example");
        return pageByPageIdRs;
    }

    private SearchByTitleRs getSearchByTitleRs() {
        List<Search> searchList = new ArrayList<>();
        Search search = new Search();
        search.setPageid(123);
        searchList.add(search);
        Query_ query = new Query_();
        query.setSearch(searchList);
        SearchByTitleRs searchByTitleRs = new SearchByTitleRs();
        searchByTitleRs.setQuery(query);
        return searchByTitleRs;
    }
}
