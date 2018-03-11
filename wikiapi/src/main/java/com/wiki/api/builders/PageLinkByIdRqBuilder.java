package com.wiki.api.builders;

import com.common.wiki.tgm.interfaces.Builder;
import com.wiki.api.WikiApiConfig;

/**
 * Билдер запроса ссылок See Also по id страницы и id секции
 * Created by Виктор on 11.03.2018.
 */
public class PageLinkByIdRqBuilder implements Builder<String> {
    private Integer pageId;
    private Integer sectionId;

    public PageLinkByIdRqBuilder(Integer pageId, Integer sectionId) {
        this.pageId = pageId;
        this.sectionId = sectionId;
    }

    public String build() {
        return String.format(WikiApiConfig.getPageLinkByIdUrl(), pageId.toString(), sectionId.toString());
    }
}
