package com.business.delegates;

import com.business.cache.ArticleCache;
import com.business.cache.BusinessCache;
import com.business.pojo.Article;
import com.wiki.api.services.WikiApiService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json.schema2pojo.dto.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * бизнес обработчик запроса на статью вики
 * Created by Виктор on 09.03.2018.
 */
public class ArticleAnswer implements AnswerDelegate {
    private static Logger LOGGER = LoggerFactory.getLogger(ArticleAnswer.class);

    @Autowired
    @Qualifier("WikiApiService")
    private WikiApiService wikiApiService;
    @Autowired
    private BusinessCache cache;

    public Article getAnswer(String articleTitleName) {
        LOGGER.info("articleTitleName " + articleTitleName);

        Article article = (Article) cache.getCachedObj(articleTitleName.toLowerCase());
        if (article != null)
            return article;

        Integer pageId = getPageId(articleTitleName);

        if (pageId != null) {
            PageByPageIdRs pageByPageIdResponse = wikiApiService.getPageContentById(pageId);

            article = new Article();
            article.setAnswer(Jsoup.clean(pageByPageIdResponse.getExtract(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)));
            article.setUrlToRealPage(pageByPageIdResponse.getFullurl());
            article.setSeeAlsoLinks(getSeeAlsoLinks(pageId));
            ((ArticleCache) cache).getCache().put(articleTitleName.toLowerCase(), article);
            return article;
        } else {
            LOGGER.info("Not found articleTitleName " + articleTitleName);
            return null;
        }
    }

    private Integer getPageId(String articleTitleName) {
        SearchByTitleRs rs = wikiApiService.getPageIdByTitle(articleTitleName);
        if (rs.getQuery() != null && rs.getQuery().getSearch() != null && rs.getQuery().getSearch().size() > 0) {
            return rs.getQuery().getSearch().get(0).getPageid();
        }
        return null;
    }

    private Integer getSeeAlsoSectionIndex(Integer pageId) {
        ContentsRs contents = wikiApiService.getPageSectionsById(pageId);

        if (contents.getParse() != null && contents.getParse().getSections() != null) {
            for (Section section : contents.getParse().getSections()) {
                if ("see also".equals(section.getLine().toLowerCase())) {
                    return Integer.parseInt(section.getIndex());
                }
            }
        }

        return null;
    }

    private List<String> getSeeAlsoLinks(Integer pageId) {
        Integer seeAlsoSectionIndex = getSeeAlsoSectionIndex(pageId);

        if (seeAlsoSectionIndex != null) {
            LinksRs linksRs = wikiApiService.getPageLinkById(pageId, getSeeAlsoSectionIndex(pageId));
            List<String> links = new ArrayList<>();

            int toIndex = linksRs.getParse().getLinks().size() >= 4 ? 4 : linksRs.getParse().getLinks().size();
            for (Link link : linksRs.getParse().getLinks().subList(0, toIndex)) {
                links.add(link.getAdditionalProperties().get("*").toString());
            }
            return links;
        }
        return Collections.emptyList();
    }
}
