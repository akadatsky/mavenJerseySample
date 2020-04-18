package com.akadatsky.api;

import com.akadatsky.model.User;
import com.akadatsky.util.UserUtil;
import org.graalvm.compiler.lir.LIRInstruction;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserApi {

    private User testUser = new User("Alex", 22);

    // Accept: application/json => Content-Type: application/json

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public Response getTestUserXml() {
        String resultXml = UserUtil.toXml(testUser);
        return Response.status(Response.Status.OK).entity(resultXml).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTestUserJson() {
        String resultJson = UserUtil.toJson(testUser);
        return Response.status(Response.Status.OK).entity(resultJson).build();
    }

    @GET
    @Path("/not-found")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTestUserNotFound() {
        String resultJson = "{}";
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(resultJson)
                .header("customHeader", "TEST")
                .build();
    }

    // http://127.0.0.1:12345/my-api/user/get?age=55&name=alex
    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUserGet(@DefaultValue("unknown") @QueryParam("name") String name,
                                  @DefaultValue("10")  @QueryParam("age") int age) {
        String result = String.format("Your params: name = %s, age = %s", name, age);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("/{name}/{age}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUserPath(@PathParam("name") String name, @PathParam("age") int age) {
        String result = String.format("Your params: name = %s, age = %s", name, age);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserForm(@FormParam("name") String name, @FormParam("age") int age) {
        User user = new User(name, age);
        String resultJson = String.format("{\"result\": \"User %s created\"}", user.getName());
        return Response.status(Response.Status.OK).entity(resultJson).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserJson(String body) {
        User user = UserUtil.fromJson(body);
        String resultJson = String.format("{\"result\": \"User %s created\"}", user.getName());
        return Response.status(Response.Status.OK).entity(resultJson).build();
    }



//    Header =  `token: 123456` =>
//    myMethod(@HeaderParam("token") String token)

}
