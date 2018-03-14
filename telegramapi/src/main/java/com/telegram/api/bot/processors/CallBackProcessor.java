package com.telegram.api.bot.processors;

import com.business.pojo.Article;
import com.telegram.api.bot.BotMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;


/**
 * Процессор обработки колбэков (событие нажатия кнопки под сообщением)
 * Created by Виктор on 09.03.2018.
 */
public class CallBackProcessor extends KeyBoardMessageProcessor {
    private static Logger LOGGER = LoggerFactory.getLogger(CallBackProcessor.class);

    public EditMessageText process(Update update) {
        CallbackQuery callback = update.getCallbackQuery();
        Message message = callback.getMessage();

        Article article = getArticle(callback.getData());
        return answerByArticle(message, article);
    }

    private EditMessageText answerByArticle(Message message, Article article) {
        if (article != null) {
            return getEditMessageText(message.getChatId(), message.getMessageId(), article);
        } else {
            LOGGER.info("Article not found");
            return BotMessageUtils.fail(message.getChatId(), message.getMessageId());
        }
    }

    private EditMessageText getEditMessageText(Long chatId, Integer messageId, Article article) {
        return BotMessageUtils.message(chatId, messageId, article.getAnswer() + "\n" + article.getUrlToRealPage(), getKeyBoard(article.getSeeAlsoLinks()));
    }

    private Article getArticle(String callData) {
        return (Article) businessHandler.getAnswer(callData);
    }
}
