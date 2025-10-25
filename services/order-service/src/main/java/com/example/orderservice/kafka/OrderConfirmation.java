package com.example.orderservice.kafka;

import com.example.orderservice.customer.CustomerResponse;
import com.example.orderservice.order.PaymentMethod;
import com.example.orderservice.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        CustomerResponse customer,
        PaymentMethod paymentMethod,
        List<PurchaseResponse> products

) {
}
