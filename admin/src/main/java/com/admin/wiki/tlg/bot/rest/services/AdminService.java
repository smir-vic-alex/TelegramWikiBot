package com.admin.wiki.tlg.bot.rest.services;

import com.business.BusinessConfig;
import com.telegram.api.bot.BotDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json.schema2pojo.dto.AddCommandRq;
import ru.json.schema2pojo.dto.CreateBotRq;
import ru.json.schema2pojo.dto.DefaultAnswer;

public class AdminService {

    @Autowired
    private BotDelegate botDelegate;

    public DefaultAnswer createBot(CreateBotRq createBotRq) {
        botDelegate.registerNewBot(createBotRq.getToken(), createBotRq.getName());

        DefaultAnswer answer = new DefaultAnswer();
        answer.setSuccess("success");
        return answer;
    }

    public DefaultAnswer addCommand(AddCommandRq addCommandRq) {
        BusinessConfig.getCommandsMap().put(addCommandRq.getCommand(), addCommandRq.getValue());
        DefaultAnswer answer = new DefaultAnswer();
        answer.setSuccess("success");
        return answer;
    }
}
