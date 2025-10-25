package com.example.product_service.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest (
        @NotNull(message ="Name can not be blank")
        String name,
        @NotNull(message ="Id can not be blank")
         Integer id,
         String description,
         @Positive(message = "Price should be Positive")
         BigDecimal price,
        @Positive(message = "Available Quantity should be Positive")
         Integer availableQuantity,
        @Positive(message = "Product Category is mandatory")
         Integer  categoryId )
{
}
