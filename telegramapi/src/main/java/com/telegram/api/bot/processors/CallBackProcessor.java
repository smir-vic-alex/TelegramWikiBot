package com.telegram.api.bot.processors;

import com.business.pojo.Article;
import com.telegram.api.bot.DefaultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;

import static java.lang.Math.toIntExact;

/**
 * Процессор обработки колбэков (событие нажатия кнопки под сообщением)
 * Created by Виктор on 09.03.2018.
 */
public class CallBackProcessor extends KeyBoardMessageProcessor {
    private static Logger LOGGER = LoggerFactory.getLogger(CallBackProcessor.class);

    public EditMessageText process(Update update) {
        String call_data = update.getCallbackQuery().getData();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        Article answer = (Article) delegate.getAnswer(call_data);
        if (answer != null) {
            return new EditMessageText()
                    .setChatId(chatId)
                    .setMessageId(toIntExact(messageId))
                    .setText(answer.getAnswer() + "\n" + answer.getUrlToRealPage())
                    .setReplyMarkup(getKeyBoard(answer.getSeeAlsoLinks()));
        } else {
            LOGGER.info("Article not found");
            return DefaultMessage.fail(chatId, toIntExact(messageId));
        }
    }
}
