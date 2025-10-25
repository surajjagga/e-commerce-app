package com.example.product_service.controller;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record ProductPurchaseResponse(

        Integer productId,
        String name,
        BigDecimal price,
        double quantity

) {
}
