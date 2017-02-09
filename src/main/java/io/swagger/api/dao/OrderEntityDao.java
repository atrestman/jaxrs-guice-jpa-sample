package io.swagger.api.dao;

import io.swagger.api.model.OrderEntity;

/**
 * Created by atrestman on 2/8/17.
 */
public class OrderEntityDao extends AbstractDao<OrderEntity> {

    public OrderEntityDao(EntityManagerContainer entityManagerContainer) {
        super(OrderEntity.class, entityManagerContainer);
    }
}
