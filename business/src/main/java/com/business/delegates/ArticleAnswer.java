package com.business.delegates;

import com.business.cache.ArticleCache;
import com.business.cache.BusinessCache;
import com.business.pojo.Article;
import com.wiki.api.services.WikiApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;
import ru.json.schema2pojo.dto.*;


/**
 * бизнес обработчик запроса на статью вики
 * Created by Виктор on 09.03.2018.
 */
public class ArticleAnswer implements AnswerBusinessHandler {
    private static final String NEXT_LINE = "\n";
    private static final String NOT_FOUND_MESSAGE = "Not found articleTitleName ";
    private static final String TITLE_NAME = "articleTitleName ";
    private static Logger LOGGER = LoggerFactory.getLogger(ArticleAnswer.class);

    @Autowired
    private WikiApiService wikiApiService;

    @Autowired
    private BusinessCache cache;

    @Autowired
    @Qualifier("seeAlsoCollector")
    private SeeAlsoCollector seeAlsoCollector;

    public Article getAnswer(String articleTitleName) {
        LOGGER.info(TITLE_NAME + articleTitleName);

        Article article = getCached(articleTitleName);
        return article != null ? article : getFromWiki(articleTitleName);
    }

    private Article getFromWiki(String articleTitleName) {
        Integer pageId = getPageId(articleTitleName);

        if (pageId == null) {
            LOGGER.info(NOT_FOUND_MESSAGE + articleTitleName);
            return null;
        }

        return buildArticle(articleTitleName, pageId);
    }

    private Article buildArticle(String articleTitleName, Integer pageId) {
        PageByPageIdRs pageByPageIdResponse = wikiApiService.getPageContentById(pageId);

        Article article = new Article();
        article.setText(buildText(pageByPageIdResponse));
        article.setSeeAlsoLinks(seeAlsoCollector.getSeeAlsoLinks(pageId));

        putInCache(articleTitleName, article);

        return article;
    }

    private String buildText(PageByPageIdRs pageByPageIdResponse) {
        return AnswerHelper.clearHtmlTags(pageByPageIdResponse.getExtract()) + NEXT_LINE + pageByPageIdResponse.getFullurl();
    }

    private void putInCache(String articleTitleName, Article article) {
        ((ArticleCache) cache).getCache().put(articleTitleName.toLowerCase(), article);
    }

    private Article getCached(String articleTitleName) {
        return (Article) cache.getCachedObj(articleTitleName.toLowerCase());
    }

    private Integer getPageId(String articleTitleName) {
        Query_ query = wikiApiService.getPageIdByTitle(articleTitleName).getQuery();

        if (query != null && !CollectionUtils.isEmpty(query.getSearch()))
            return query.getSearch().get(0).getPageid();

        return null;
    }
}
