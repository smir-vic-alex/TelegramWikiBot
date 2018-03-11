package com.wiki.api.builders;

import com.common.wiki.tgm.interfaces.Builder;
import com.wiki.api.WikiApiConfig;

/**
 * Билдер запроса контента страницы по ее id
 * Created by Виктор on 10.03.2018.
 */
public class PageContentByIdRqBuilder implements Builder<String> {
    private Integer id;

    public PageContentByIdRqBuilder(Integer id) {
        this.id = id;
    }

    public String build() {
        return String.format(WikiApiConfig.getPageContentByIdUrl(), id.toString());
    }
}
