package com.wiki.api.builders;

import com.common.wiki.tgm.interfaces.Builder;
import com.wiki.api.WikiApiConfig;

/**
 * Билдер запроса поиска id страницы по заголовку
 * Created by Виктор on 10.03.2018.
 */
public class PageIdByTitleRqBuilder implements Builder<String> {

    private String param;

    public PageIdByTitleRqBuilder(String param) {
        this.param = param.replaceAll(" ", "%20");
    }

    public String build() {
        return String.format(WikiApiConfig.getPageIdByTitleUrl(), param);
    }
}
