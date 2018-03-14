package com.business.delegates;

import com.business.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Бизнес обработчик колбэка
 * Created by Виктор on 09.03.2018.
 */
public class CallBackAnswer implements AnswerBusinessHandler {

    @Autowired
    private ArticleAnswer articleAnswer;

    @Override
    public Article getAnswer(String articleTitleName) {
        return articleAnswer.getAnswer(articleTitleName);
    }
}
