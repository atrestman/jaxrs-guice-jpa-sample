package io.swagger.swaggerizer;

import io.swagger.api.model.UserEntity;
import io.swagger.model.User;

/**
 * Created by atrestman on 2/3/17.
 */
public class UserSwaggarizer {

    public static User swaggarize(UserEntity userEntity) {
        return new User()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .id(userEntity.getId())
                .password(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .username(userEntity.getUsername())
                .userStatus(userEntity.getUserStatus())
                .firstName(userEntity.getFirstName())
                .lastName((userEntity.getLastName()));
    }

    public static UserEntity swaggarize(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setId(user.getId());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhone(user.getPhone());
        userEntity.setUsername(user.getUsername());
        userEntity.setUserStatus(user.getUserStatus());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName((user.getLastName()));

        return userEntity;
    }
}
