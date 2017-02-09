package io.swagger.api.dao;

import io.swagger.api.model.TagEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by atrestman on 2/8/17.
 */
public class TagEntityDao extends AbstractDao<TagEntity> {

    public TagEntityDao(EntityManagerContainer entityManagerContainer) {
        super(TagEntity.class, entityManagerContainer);
    }

    public TagEntity getByPetId(Long petId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TagEntity> cq = cb.createQuery(TagEntity.class);
        Root<TagEntity> tag = cq.from(TagEntity.class);

        Predicate cond1 = cb.equal(tag.get("petId"), petId);
        cq.where(cond1);

        return getEntityManager().createQuery(cq).getSingleResult();
    }
}
