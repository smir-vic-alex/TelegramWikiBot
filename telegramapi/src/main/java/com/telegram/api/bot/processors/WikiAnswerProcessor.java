package com.telegram.api.bot.processors;

import com.business.pojo.Article;
import com.telegram.api.bot.DefaultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Обработчик сообщения с запросом на вики статью
 * Created by Виктор on 09.03.2018.
 */
public class WikiAnswerProcessor extends KeyBoardMessageProcessor {
    private static Logger LOGGER = LoggerFactory.getLogger(WikiAnswerProcessor.class);

    public SendMessage process(Update update) {
        Article article = (Article) delegate.getAnswer(update.getMessage().getText());
        if (article != null) {
            return new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(article.getAnswer() + "\n" + article.getUrlToRealPage())
                    .setReplyMarkup(getKeyBoard(article.getSeeAlsoLinks()));
        }
        LOGGER.info("Article not found");
        return DefaultMessage.fail(update.getMessage().getChatId());
    }
}
