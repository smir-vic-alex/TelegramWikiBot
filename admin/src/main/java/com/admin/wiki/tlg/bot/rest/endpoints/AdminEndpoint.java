package com.admin.wiki.tlg.bot.rest.endpoints;

import com.admin.wiki.tlg.bot.rest.services.AdminService;
import com.common.wiki.tgm.EndpointBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.json.schema2pojo.dto.AddCommandRq;
import ru.json.schema2pojo.dto.CreateBotRq;
import ru.json.schema2pojo.dto.DefaultAnswer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminEndpoint extends EndpointBase<AdminService> {

    @Autowired
    @Qualifier("adminService")
    @Override
    public void setService(AdminService service) {
        super.setService(service);
    }

    @Path("/put/bot")
    @POST
    public DefaultAnswer putToken(CreateBotRq createBotRq) {
        return getService().createBot(createBotRq);
    }

    @Path("/add/command")
    @POST
    public DefaultAnswer addCommand(AddCommandRq addCommandRq) {
        return getService().addCommand(addCommandRq);
    }
}
