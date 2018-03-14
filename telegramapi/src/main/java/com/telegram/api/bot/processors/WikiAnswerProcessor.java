package com.telegram.api.bot.processors;

import com.business.pojo.Article;
import com.telegram.api.bot.BotMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Обработчик сообщения с запросом на вики статью
 * Created by Виктор on 09.03.2018.
 */
public class WikiAnswerProcessor extends KeyBoardMessageProcessor {
    private static Logger LOGGER = LoggerFactory.getLogger(WikiAnswerProcessor.class);

    public SendMessage process(Update update) {
        Message message = update.getMessage();
        Article article = getArticleAnswer(message);
        return answerByArticle(message, article);
    }

    private SendMessage answerByArticle(Message message, Article article) {
        if (article != null)
            return sendAnswer(message, article);
        else {
            LOGGER.info("Article not found " + message.toString());
            return BotMessageUtils.fail(message.getChatId());
        }
    }

    private SendMessage sendAnswer(Message message, Article article) {
        return BotMessageUtils.message(message.getChatId(), article.getAnswer() + "\n" + article.getUrlToRealPage(), getKeyBoard(article.getSeeAlsoLinks()));
    }

    private Article getArticleAnswer(Message message) {
        return (Article) businessHandler.getAnswer(message.getText());
    }
}
