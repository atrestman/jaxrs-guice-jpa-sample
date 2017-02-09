package io.swagger.api.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;

/**
 * Created by atrestman on 2/6/17.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntityManagerContainer {
    @Inject
    private Provider<EntityManager> entityManager;
}
