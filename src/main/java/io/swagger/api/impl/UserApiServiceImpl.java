package io.swagger.api.impl;

import com.google.inject.Inject;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.UserApiService;
import io.swagger.api.dao.UserEntityDao;
import io.swagger.api.model.UserEntity;
import io.swagger.model.User;
import io.swagger.swaggerizer.UserSwaggarizer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserApiServiceImpl extends UserApiService {

    @Inject
    private UserEntityDao userEntityDao;

    @Override
    public Response createUser(User body, SecurityContext securityContext) throws NotFoundException {

        UserEntity userEntity = UserSwaggarizer.swaggarize(body);
        userEntityDao.create(userEntity);
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response createUsersWithArrayInput(List<User> body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response createUsersWithListInput(List<User> body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deleteUser(String username, SecurityContext securityContext) throws NotFoundException {
        UserEntity userEntity = userEntityDao.getByUsername(username);
        if (userEntity != null) {
            userEntityDao.remove(userEntity.getId());
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getUserByName(String username, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        UserEntity userEntity = userEntityDao.getByUsername(username);
        User user = UserSwaggarizer.swaggarize(userEntity);

        return Response.ok().entity(user).build();
    }
    @Override
    public Response loginUser(String username, String password, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response logoutUser(SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateUser(String username, User body, SecurityContext securityContext) throws NotFoundException {
        UserEntity existingUserEntity = userEntityDao.getByUsername(username);
        if (existingUserEntity != null) {
            UserEntity updatedUser = UserSwaggarizer.swaggarize(body);
            updatedUser.setId(existingUserEntity.getId());
            userEntityDao.update(updatedUser);
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
