package com.admin.wiki.tlg.bot.rest.services;

import com.admin.wiki.tlg.bot.rest.MessageUtils;
import com.business.BusinessConfig;
import com.telegram.api.bot.BotService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json.schema2pojo.dto.AddCommandRq;
import ru.json.schema2pojo.dto.CreateBotRq;
import ru.json.schema2pojo.dto.DefaultAnswer;

/**
 * Сервис-обработчик запросов в админскую часть
 */
public class AdminService {

    private static final String INVALID_COMMAND_MESSAGE = "Invalid AddCommandRq request";
    private static final String INVALID_CREATE_BOT_MESSAGE = "Invalid CreateBotRq request";
    private static Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private BotService botService;

    @Autowired
    private BusinessConfig config;

    /**
     * Создать и зарегестрировать новый инстанс вики бота
     *
     * @param createBotRq - запрос на создание бота
     * @return ответ об успешности пользователю
     */
    public DefaultAnswer createBot(CreateBotRq createBotRq) {
        LOGGER.debug("Start create bot");

        try {
            validate(createBotRq);
            botService.register(createBotRq.getToken(), createBotRq.getName());
        } catch (Throwable e) {
            LOGGER.error("Error create bot", e);
            return MessageUtils.fail();
        }

        LOGGER.debug("Success create bot");
        return MessageUtils.success();
    }

    /**
     * Добавить/обновить команду с ответом
     *
     * @param addCommandRq - запрос на создание команды
     * @return ответ об успешности пользователю
     */
    public DefaultAnswer addCommand(AddCommandRq addCommandRq) {
        LOGGER.debug("Start create command");

        try {
            validate(addCommandRq);
            config.getCommandsMap().put(addCommandRq.getCommand(), addCommandRq.getValue());
        } catch (Throwable e) {
            LOGGER.error("Error create command", e);
            return MessageUtils.fail();
        }

        LOGGER.debug("Success create command");
        return MessageUtils.success();
    }

    private void validate(AddCommandRq addCommandRq) {
        if (StringUtils.isNotEmpty(addCommandRq.getCommand()))
            if (addCommandRq.getCommand().startsWith("/"))
                return;
        LOGGER.error(INVALID_COMMAND_MESSAGE);
        throw new RuntimeException(INVALID_COMMAND_MESSAGE);

    }

    private void validate(CreateBotRq createBotRq) {
        if (StringUtils.isEmpty(createBotRq.getName()) || StringUtils.isEmpty(createBotRq.getToken())) {
            LOGGER.error(INVALID_CREATE_BOT_MESSAGE);
            throw new RuntimeException(INVALID_CREATE_BOT_MESSAGE);
        }
    }
}
