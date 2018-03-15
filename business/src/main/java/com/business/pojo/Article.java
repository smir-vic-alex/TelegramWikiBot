package com.business.pojo;

import java.util.List;

/**
 * Контейнер ответа вики статьи
 * Created by Виктор on 10.03.2018.
 */
public class Article implements Answer {
    private String text;
    private List<String> seeAlsoLinks;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getSeeAlsoLinks() {
        return seeAlsoLinks;
    }

    public void setSeeAlsoLinks(List<String> seeAlsoLinks) {
        this.seeAlsoLinks = seeAlsoLinks;
    }
}
