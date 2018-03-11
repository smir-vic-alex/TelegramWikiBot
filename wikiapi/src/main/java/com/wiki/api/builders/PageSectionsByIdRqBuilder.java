package com.wiki.api.builders;

import com.common.wiki.tgm.interfaces.Builder;
import com.wiki.api.WikiApiConfig;

/**
 * Билдер запроса секций (для поиска секции See also) по id страницы
 * Created by Виктор on 11.03.2018.
 */
public class PageSectionsByIdRqBuilder implements Builder<String> {
    private Integer pageId;

    public PageSectionsByIdRqBuilder(Integer pageId) {
        this.pageId = pageId;
    }

    public String build() {
        return String.format(WikiApiConfig.getPageSectionsByIdUrl(), pageId.toString());
    }
}
