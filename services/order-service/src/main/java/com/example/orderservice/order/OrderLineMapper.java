package com.example.orderservice.order;

import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLineMapper(OrderLineRequest orderLineRequest) {
       return OrderLine.builder().
               id(orderLineRequest.id()).
               productId(orderLineRequest.productId()).
               quantity(orderLineRequest.quantity()).
               order(Order.builder().id(orderLineRequest.orderId()).build()).
               build();
    }

    public OrderLineResponse fromOrderLineMapper( OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
