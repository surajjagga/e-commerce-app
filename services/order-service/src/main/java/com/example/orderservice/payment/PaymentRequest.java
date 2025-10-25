package com.example.orderservice.payment;

import com.example.orderservice.customer.CustomerResponse;
import com.example.orderservice.order.PaymentMethod;

import java.math.BigDecimal;


public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {


}
