package com.example.orderservice.order;

import java.math.BigDecimal;

public record OrderResponse (
        Integer orderId,
        String orderReference,
        PaymentMethod paymentMethod,
        BigDecimal totalAmount,
        String customerId
){
}
