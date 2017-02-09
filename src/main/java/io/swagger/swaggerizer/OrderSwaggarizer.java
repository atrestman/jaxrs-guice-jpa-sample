package io.swagger.swaggerizer;

import io.swagger.api.model.OrderEntity;
import io.swagger.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by atrestman on 2/8/17.
 */
public class OrderSwaggarizer {

    public static Order swaggerize(OrderEntity orderEntity) {

        Date shipDate = new Date(orderEntity.getShipDateTimestamp());
        Order.StatusEnum status = Order.StatusEnum.valueOf(orderEntity.getStatus());

        return new Order()
                .id(orderEntity.getId())
                .complete(orderEntity.getComplete())
                .quantity(orderEntity.getQuantity())
                .shipDate(shipDate)
                .status(status)
                .petId(orderEntity.getPetId());
    }

    public static List<Order> swaggerise(List<OrderEntity> orderEntities) {
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity: orderEntities) {
            orders.add(OrderSwaggarizer.swaggerize(orderEntity));
        }
        return orders;
    }

    public static OrderEntity swaggerize(Order order) {

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId(order.getId());
        orderEntity.setComplete(order.getComplete());
        orderEntity.setPetId(order.getPetId());
        orderEntity.setQuantity(order.getQuantity());
        orderEntity.setShipDateTimestamp(order.getShipDate().getTime());
        orderEntity.setStatus(order.getStatus().toString());

        return orderEntity;
    }
}
