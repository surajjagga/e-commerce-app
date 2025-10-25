package com.example.product_service.controller;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message="ProductId can not be null")
        Integer productId,
        @NotNull(message="Quantity is mandatory")
        double quantity
) {
}
