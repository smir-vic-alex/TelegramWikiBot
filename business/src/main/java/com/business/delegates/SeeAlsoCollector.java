package com.business.delegates;

import com.business.BusinessConfig;
import com.wiki.api.services.WikiApiService;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json.schema2pojo.dto.Link;
import ru.json.schema2pojo.dto.Parse;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SeeAlsoCollector {

    private static final String SEE_ALSO_FIELD_KEY = "*";
    private static final String SEE_ALSO_FIELD = "see also";

    @Autowired
    private WikiApiService wikiApiService;

    /**
     * Найти See also ссылки
     *
     * @param pageId - id страницы
     * @return список ссылок
     */
    public List<String> getSeeAlsoLinks(Integer pageId) {
        Integer seeAlsoSectionIndex = getSeeAlsoSectionIndex(pageId);
        if (seeAlsoSectionIndex == null)
            return Collections.emptyList();

        List<Link> linksList = wikiApiService.getPageLinkById(pageId, seeAlsoSectionIndex).getParse().getLinks();
        return limitedSizeLinksList(linksList).stream()
                .map(link -> link.getAdditionalProperties().get(SEE_ALSO_FIELD_KEY).toString())
                .collect(toList());
    }

    private Integer getSeeAlsoSectionIndex(Integer pageId) {
        Parse parse = wikiApiService.getPageSectionsById(pageId).getParse();

        if (parse != null && parse.getSections() != null) {
            return parse.getSections().stream()
                    .filter(section -> SEE_ALSO_FIELD.equals(section.getLine().toLowerCase()))
                    .findFirst()
                    .map(section -> Integer.parseInt(section.getIndex())).orElse(null);
        }
        return null;
    }

    private List<Link> limitedSizeLinksList(List<Link> linksList) {
        int defaultSize = BusinessConfig.getSeeAlsoListSize();
        int toIndex = linksList.size() >= defaultSize ? defaultSize : linksList.size();
        return linksList.subList(0, toIndex);
    }
}
