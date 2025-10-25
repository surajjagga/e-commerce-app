package com.example.orderservice.order;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
        @NotNull(message ="Product Id is mandatory")
        Integer productId,
        @NotNull(message ="Quantity is mandatory")
        double quantity

) {
}
