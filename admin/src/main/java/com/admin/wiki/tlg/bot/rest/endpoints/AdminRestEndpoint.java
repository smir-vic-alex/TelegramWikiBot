package com.admin.wiki.tlg.bot.rest.endpoints;

import com.admin.wiki.tlg.bot.rest.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import ru.json.schema2pojo.dto.AddCommandRq;
import ru.json.schema2pojo.dto.CreateBotRq;
import ru.json.schema2pojo.dto.DefaultAnswer;
import ru.json.schema2pojo.dto.LoginRq;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminRestEndpoint {

    @Autowired
    private AdminService adminService;

    @Path("/put/bot")
    @POST
    public DefaultAnswer putToken(CreateBotRq createBotRq) {
        return adminService.createBot(createBotRq);
    }

    @Path("/login")
    @POST
    public DefaultAnswer login(LoginRq loginRq) {
        return adminService.login(loginRq);
    }

    @Path("/add/command")
    @POST
    public DefaultAnswer changeConfig(AddCommandRq addCommandRq) {
        return adminService.changeConfig(addCommandRq);
    }
}
