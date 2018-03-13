package com.services.rest.endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminEndpoint {

    @Path("/echo")
    @GET
    public Response echo() {
        return Response.ok("{\"Echo\":\"echo\"}").build();
    }
}
