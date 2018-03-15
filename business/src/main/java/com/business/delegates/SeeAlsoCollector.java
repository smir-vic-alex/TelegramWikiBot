package com.business.delegates;

import com.business.BusinessConfig;
import com.wiki.api.services.WikiApiService;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json.schema2pojo.dto.Link;
import ru.json.schema2pojo.dto.Parse;
import ru.json.schema2pojo.dto.Section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeeAlsoCollector {

    private static final String SEE_ALSO_FIELD_KEY = "*";
    private static final String SEE_ALSO_FIELD = "see also";

    @Autowired
    private WikiApiService wikiApiService;

    /**
     * Найти See also ссылки
     * @param pageId - id страницы
     * @return список ссылок
     */
    public List<String> getSeeAlsoLinks(Integer pageId) {
        Integer seeAlsoSectionIndex = getSeeAlsoSectionIndex(pageId);
        if (seeAlsoSectionIndex == null)
            return Collections.emptyList();

        List<Link> linksList = wikiApiService.getPageLinkById(pageId, seeAlsoSectionIndex).getParse().getLinks();
        List<String> links = new ArrayList<>(BusinessConfig.getSeeAlsoListSize());

        for (Link link : getLinksSubList(linksList)) {
            links.add(link.getAdditionalProperties().get(SEE_ALSO_FIELD_KEY).toString());
        }
        return links;
    }

    private Integer getSeeAlsoSectionIndex(Integer pageId) {
        Parse parse = wikiApiService.getPageSectionsById(pageId).getParse();

        if (parse != null && parse.getSections() != null) {
            for (Section section : parse.getSections()) {
                if (SEE_ALSO_FIELD.equals(section.getLine().toLowerCase())) {
                    return Integer.parseInt(section.getIndex());
                }
            }
        }
        return null;
    }

    private List<Link> getLinksSubList(List<Link> linksList) {
        int defaultSize = BusinessConfig.getSeeAlsoListSize();
        int toIndex = linksList.size() >= defaultSize ? defaultSize : linksList.size();
        return linksList.subList(0, toIndex);
    }
}
