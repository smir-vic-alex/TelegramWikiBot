package com.wiki.api.services;

import com.common.wiki.tgm.interfaces.BusinessService;
import com.wiki.api.utils.WikiApiUtils;
import com.wiki.api.WikiExecutor;
import com.wiki.api.builders.PageContentByIdRqBuilder;
import com.wiki.api.builders.PageIdByTitleRqBuilder;
import com.wiki.api.builders.PageLinkByIdRqBuilder;
import com.wiki.api.builders.PageSectionsByIdRqBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.json.schema2pojo.dto.*;

/**
 * Бизнес сервис для работы с вики
 * Created by Виктор on 10.03.2018.
 */
public class WikiApiService implements BusinessService {

    private static Logger LOGGER = LoggerFactory.getLogger(WikiApiService.class);

    public SearchByTitleRs getPageIdByTitle(final String title) {
        String params = new PageIdByTitleRqBuilder(title).build();

        LOGGER.debug("getPageIdByTitle, params " + params);
        return new WikiExecutor<SearchByTitleRs>().execute(params, WikiApiUtils::mapJsonToSearchByTitleRs);
    }

    public PageByPageIdRs getPageContentById(final Integer id) {
        String params = new PageContentByIdRqBuilder(id).build();
        LOGGER.debug("getPageContentById, params " + params);

        SearchByIdRs searchByIdRs = new WikiExecutor<SearchByIdRs>().execute(params, WikiApiUtils::mapJsonToSearchByIdRs);

        return WikiApiUtils.mapToPageByPageIdRs(searchByIdRs, id);
    }

    public ContentsRs getPageSectionsById(final Integer id) {
        String params = new PageSectionsByIdRqBuilder(id).build();
        LOGGER.debug("getPageSectionsById, params " + params);

        return new WikiExecutor<ContentsRs>().execute(params, WikiApiUtils::mapJsonToContentsRs);
    }

    public LinksRs getPageLinkById(final Integer id, final Integer sectionId) {
        String params = new PageLinkByIdRqBuilder(id, sectionId).build();
        LOGGER.debug("getPageLinkById, params " + params);

        return new WikiExecutor<LinksRs>().execute(params, WikiApiUtils::mapJsonToLinksRs);
    }
}
