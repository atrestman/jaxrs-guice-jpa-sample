package io.swagger.api.impl;

import com.google.inject.Inject;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.StoreApiService;
import io.swagger.api.dao.OrderEntityDao;
import io.swagger.api.model.OrderEntity;
import io.swagger.model.Order;
import io.swagger.swaggerizer.OrderSwaggarizer;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;


@NoArgsConstructor
public class StoreApiServiceImpl extends StoreApiService {

    @Inject
    OrderEntityDao orderEntityDao;

    @Override
    public Response deleteOrder(Long orderId, SecurityContext securityContext) throws NotFoundException {
        orderEntityDao.remove(orderId);
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getInventory(SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        List<OrderEntity> orderEntities = orderEntityDao.findAll();
        List<Order> orders = OrderSwaggarizer.swaggerise(orderEntities);
        return Response.ok().entity(orders).build();
    }
    @Override
    public Response getOrderById(Long orderId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        Order order = null;
        OrderEntity orderEntity = orderEntityDao.find(orderId);
        if (orderEntity != null) {
            order = OrderSwaggarizer.swaggerize(orderEntity);
        }
        return Response.ok().entity(order).build();
    }
    @Override
    public Response placeOrder(Order body, SecurityContext securityContext) throws NotFoundException {
        OrderEntity orderEntity = OrderSwaggarizer.swaggerize(body);
        orderEntityDao.create(orderEntity);

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
