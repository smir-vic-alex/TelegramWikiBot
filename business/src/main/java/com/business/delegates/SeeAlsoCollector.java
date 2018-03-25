package com.business.delegates;

import com.business.BusinessConfig;
import com.common.wiki.tgm.QuickException;
import com.wiki.api.services.WikiApiService;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json.schema2pojo.dto.Link;
import ru.json.schema2pojo.dto.Parse;
import ru.json.schema2pojo.dto.Parse_;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SeeAlsoCollector {

    private static final String SEE_ALSO_FIELD_KEY = "*";
    private static final String SEE_ALSO_FIELD = "see also";
    private static final int TELEGRAM_CALL_BACK_TEXT_BUTTON_LIMIT = 64;
    private static final String SEE_ALSO_LINKS_FOR_DOES_NOT_EXIST_MSG = "See Also links for does not exist";
    private static final String SEE_ALSO_LINKS_FOR_PAGE_ID_MSG = "See Also links for pageId ";
    private static final String DOES_NOT_EXIST_MSG = " does not exist";

    @Autowired
    private WikiApiService wikiApiService;

    /**
     * Найти See also ссылки
     *
     * @param pageId - id страницы
     * @return список ссылок
     */
    public List<String> getSeeAlsoLinks(Integer pageId) {
        try {
            return buildSeeAlsoList(pageId);
        } catch (Throwable e) {
            return Collections.emptyList();
        }
    }

    private List<String> buildSeeAlsoList(Integer pageId) {
        List<Link> linksList = getPageSeeAlsoLinks(pageId);

        return linksList.stream()
                .map(link -> link.getAdditionalProperties().get(SEE_ALSO_FIELD_KEY).toString())
                .filter(link -> link.length() < TELEGRAM_CALL_BACK_TEXT_BUTTON_LIMIT)
                .limit(BusinessConfig.getSeeAlsoListSize())
                .collect(toList());
    }

    private List<Link> getPageSeeAlsoLinks(Integer pageId) {
        Integer seeAlsoSectionIndex = findSeeAlsoSectionIndex(pageId);
        Parse_ parse = wikiApiService.getPageLinkById(pageId, seeAlsoSectionIndex).getParse();
        checkOnEmpty(parse, parse.getLinks());

        return parse.getLinks();
    }

    private Integer findSeeAlsoSectionIndex(Integer pageId) {
        return getParse(pageId).getSections().stream()
                .filter(section -> SEE_ALSO_FIELD.equals(section.getLine().toLowerCase()))
                .findFirst()
                .map(section -> Integer.parseInt(section.getIndex()))
                .orElseThrow(() -> new SeeAlsoException(SEE_ALSO_LINKS_FOR_PAGE_ID_MSG + pageId + DOES_NOT_EXIST_MSG));
    }

    private Parse getParse(Integer pageId) {
        Parse parse = wikiApiService.getPageSectionsById(pageId).getParse();
        checkOnEmpty(parse, parse.getSections());

        return parse;
    }

    private void checkOnEmpty(Object obj, List objList) {
        if (obj == null || objList == null)
            throw new SeeAlsoException(SEE_ALSO_LINKS_FOR_DOES_NOT_EXIST_MSG);
    }

    private class SeeAlsoException extends QuickException {

        private SeeAlsoException(String message) {
            super(message);
        }
    }
}
