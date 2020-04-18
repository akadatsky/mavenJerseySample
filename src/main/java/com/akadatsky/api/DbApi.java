package com.akadatsky.api;

import com.akadatsky.model.User;
import com.akadatsky.util.UserUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/db")
public class DbApi {

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(String json) {
        User user = UserUtil.fromJson(json);
        User savedUser = UserUtil.addUser(user);
        if (savedUser != null) {
            String resultJson = "{\"id\": " + savedUser.getId() + "}";
            return Response.status(Response.Status.OK).entity(resultJson).build();
        } else {
            String resultJson = "{\"result\": \"failed to save user\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(resultJson).build();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersList() {
        List<User> users = UserUtil.getUsers();
        String resultJson = UserUtil.toJson(users);
        return Response.status(Response.Status.OK).entity(resultJson).build();
    }

}
