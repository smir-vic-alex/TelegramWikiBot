package com.business;

import com.business.pojo.Command;

/**
 * Бизнес обработчик запроса с командой (напр. /start или /info)
 * Created by Виктор on 11.03.2018.
 */
public class CommandAnswer implements AnswerDelegate {

    @Override
    public Command getAnswer(String cmd) {
        return new Command(BusinessConfig.getCommandsMap().get(cmd.toLowerCase()));
    }
}
