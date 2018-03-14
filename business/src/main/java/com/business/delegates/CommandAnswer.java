package com.business.delegates;

import com.business.BusinessConfig;
import com.business.pojo.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Бизнес обработчик запроса с командой (напр. /start или /info)
 * Created by Виктор on 11.03.2018.
 */
public class CommandAnswer implements AnswerBusinessHandler {

    @Autowired
    private BusinessConfig config;

    @Override
    public Command getAnswer(String cmd) {
        return new Command(config.getCommandsMap().get(cmd.toLowerCase()));
    }
}
