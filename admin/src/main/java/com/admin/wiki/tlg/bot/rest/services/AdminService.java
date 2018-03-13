package com.admin.wiki.tlg.bot.rest.services;

import com.business.delegates.BotDelegate;
import ru.json.schema2pojo.dto.AddCommandRq;
import ru.json.schema2pojo.dto.CreateBotRq;
import ru.json.schema2pojo.dto.DefaultAnswer;
import ru.json.schema2pojo.dto.LoginRq;

public class AdminService {

    public DefaultAnswer createBot(CreateBotRq createBotRq) {
        BotDelegate botDelegate = new BotDelegate();
        botDelegate.registerNewBot(createBotRq.getToken(), createBotRq.getName());
        return new DefaultAnswer();
    }

    public DefaultAnswer login(LoginRq loginRq) {
        return new DefaultAnswer();
    }

    public DefaultAnswer changeConfig(AddCommandRq addCommandRq) {
        return new DefaultAnswer();
    }
}
