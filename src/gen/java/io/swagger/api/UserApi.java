package io.swagger.api;

import com.google.inject.Inject;
import io.swagger.annotations.ApiParam;
import io.swagger.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/user")


@io.swagger.annotations.Api(description = "the user API")

public class UserApi  {

    @Inject private UserApiService delegate;

    @POST
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create user", notes = "This can only be done by the logged in user.", response = void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = void.class) })
    public Response createUser(@ApiParam(value = "Created user object" ,required=true) User body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createUser(body,securityContext);
    }
    @POST
    @Path("/createWithArray")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates list of users with given input array", notes = "", response = void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = void.class) })
    public Response createUsersWithArrayInput(@ApiParam(value = "List of user object" ,required=true) List<User> body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createUsersWithArrayInput(body,securityContext);
    }
    @POST
    @Path("/createWithList")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates list of users with given input array", notes = "", response = void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = void.class) })
    public Response createUsersWithListInput(@ApiParam(value = "List of user object" ,required=true) List<User> body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createUsersWithListInput(body,securityContext);
    }
    @DELETE
    @Path("/{username}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user.", response = void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid username supplied", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = void.class) })
    public Response deleteUser(@ApiParam(value = "The name that needs to be deleted",required=true) @PathParam("username") String username
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteUser(username,securityContext);
    }
    @GET
    @Path("/{username}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get user by user name", notes = "", response = User.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid username supplied", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = User.class) })
    public Response getUserByName(@ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ",required=true) @PathParam("username") String username
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getUserByName(username,securityContext);
    }
    @GET
    @Path("/login")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs user into the system", notes = "", response = String.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid username/password supplied", response = String.class) })
    public Response loginUser(@ApiParam(value = "The user name for login",required=true) @QueryParam("username") String username
,@ApiParam(value = "The password for login in clear text",required=true) @QueryParam("password") String password
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.loginUser(username,password,securityContext);
    }
    @GET
    @Path("/logout")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs out current logged in user session", notes = "", response = void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = void.class) })
    public Response logoutUser(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.logoutUser(securityContext);
    }
    @PUT
    @Path("/{username}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updated user", notes = "This can only be done by the logged in user.", response = void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid user supplied", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = void.class) })
    public Response updateUser(@ApiParam(value = "name that need to be updated",required=true) @PathParam("username") String username
,@ApiParam(value = "Updated user object" ,required=true) User body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateUser(username,body,securityContext);
    }
}
