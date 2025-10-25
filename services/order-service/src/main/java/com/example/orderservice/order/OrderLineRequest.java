package com.example.orderservice.order;

public record OrderLineRequest(
        Integer id,
        Integer productId,
        double quantity,
        Integer orderId
) {
}
