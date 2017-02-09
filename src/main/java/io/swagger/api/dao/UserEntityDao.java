package io.swagger.api.dao;

import io.swagger.api.model.UserEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by atrestman on 2/3/17.
 */
public class UserEntityDao extends AbstractDao<UserEntity> {

    public UserEntityDao(EntityManagerContainer entityManagerContainer) {
       super(UserEntity.class, entityManagerContainer);
    }

    public UserEntity getByUsername(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> person = cq.from(UserEntity.class);

        Predicate cond1 = cb.equal(person.get("username"), name);
        cq.where(cond1);

        return getEntityManager().createQuery(cq).getSingleResult();
    }

}
