package com.business.pojo;

import java.util.List;

/**
 * Контейнер ответа вики статьи
 * Created by Виктор on 10.03.2018.
 */
public class Article implements Answer {
    private String answer;
    private String urlToRealPage;
    private List<String> seeAlsoLinks;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUrlToRealPage() {
        return urlToRealPage;
    }

    public void setUrlToRealPage(String urlToRealPage) {
        this.urlToRealPage = urlToRealPage;
    }

    public List<String> getSeeAlsoLinks() {
        return seeAlsoLinks;
    }

    public void setSeeAlsoLinks(List<String> seeAlsoLinks) {
        this.seeAlsoLinks = seeAlsoLinks;
    }
}
