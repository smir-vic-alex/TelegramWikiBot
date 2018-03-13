package com.wiki.api;


import com.common.wiki.tgm.PropertiesService;

/**
 * Конфиг настроек для вики модуля
 * Created by Виктор on 11.03.2018.
 */
public class WikiApiConfig {

    public static final String PAGE_LINK_BY_ID_URL_KEY = "page.link.by.id.url";
    public static final String PAGE_ID_BY_TITLE_URL_KEY = "page.id.by.title.url";
    public static final String PAGE_CONTENT_BY_ID_URL_KEY = "page.content.by.id.url";
    public static final String PAGE_SECTIONS_BY_ID_URL_KEY = "page.sections.by.id.url";
    public static final String WIKI_BASE_URL = "wiki.base.url";
    public static final String WIKI_RS_FORMAT = "wiki.format";

    public static String getPageLinkByIdUrl() {
        return PropertiesService.getInstance().get(PAGE_LINK_BY_ID_URL_KEY);
    }

    public static String getPageIdByTitleUrl() {
        return PropertiesService.getInstance().get(PAGE_ID_BY_TITLE_URL_KEY);
    }

    public static String getPageContentByIdUrl() {
        return PropertiesService.getInstance().get(PAGE_CONTENT_BY_ID_URL_KEY);
    }

    public static String getPageSectionsByIdUrl() {
        return PropertiesService.getInstance().get(PAGE_SECTIONS_BY_ID_URL_KEY);
    }

    public static String getWikiRootUrl() {
        return PropertiesService.getInstance().get(WIKI_BASE_URL);
    }

    public static String getWikiRsFormat() {
        return PropertiesService.getInstance().get(WIKI_RS_FORMAT);
    }

    public static String getBaseUrl() {
        return getWikiRootUrl() + "?" + getWikiRsFormat() + "&";
    }
}
