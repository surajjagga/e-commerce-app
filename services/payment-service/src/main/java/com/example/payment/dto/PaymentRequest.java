package com.example.payment.dto;

import com.example.payment.payment.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;


public record PaymentRequest(
Integer id,
BigDecimal amount,
PaymentMethod paymentMethod,
Integer orderId,
Customer customer
) {
}
