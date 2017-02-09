package io.swagger.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by atrestman on 2/3/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id = null;

    private String username = null;

    private String firstName = null;

    private String lastName = null;

    private String email = null;

    private String password = null;

    private String phone = null;

    private Integer userStatus = null;
}
