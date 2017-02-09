package io.swagger.api.dao;

import io.swagger.api.model.CategoryEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by atrestman on 2/8/17.
 */
public class CategoryEntityDao extends AbstractDao<CategoryEntity> {

    public CategoryEntityDao(EntityManagerContainer entityManagerContainer) {
        super(CategoryEntity.class, entityManagerContainer);
    }

    public CategoryEntity getByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
        Root<CategoryEntity> category = cq.from(CategoryEntity.class);

        Predicate cond1 = cb.equal(category.get("name"), name);
        cq.where(cond1);

        return getEntityManager().createQuery(cq).getSingleResult();
    }
}
