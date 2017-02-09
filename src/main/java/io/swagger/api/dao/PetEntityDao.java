package io.swagger.api.dao;

import io.swagger.api.model.PetEntity;

/**
 * Created by atrestman on 2/8/17.
 */
public class PetEntityDao extends AbstractDao<PetEntity> {

    public PetEntityDao(EntityManagerContainer entityManagerContainer) {
        super(PetEntity.class, entityManagerContainer);
    }
}
