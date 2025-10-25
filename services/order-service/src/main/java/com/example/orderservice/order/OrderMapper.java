package com.example.orderservice.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

   public Order toOrder(OrderRequest orderRequest) {
       return Order.builder()
               .reference(orderRequest.reference())
               .id(orderRequest.id())
               .customer_id(orderRequest.customer_id())
               .totalAmount(orderRequest.totalAmount())
               .payment_method(orderRequest.payment_method())
               .build();

   }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(order.getId(),order.getReference(),order.getPayment_method(),order.getTotalAmount(),order.getCustomer_id());

    }
}
