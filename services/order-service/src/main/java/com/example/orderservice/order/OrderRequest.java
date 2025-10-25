package com.example.orderservice.order;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(
        Integer id,
        @NotEmpty(message ="CustomerId can not be empty")
        @NotNull (message ="CustomerId can not be null")
        @NotBlank (message ="CustomerId can not be blank")
        String customer_id,
        String reference,
        @NotEmpty(message= "You should buy at least one product")
        List<PurchaseRequest> products,
        @Positive(message = "TotalAmount should be Positive")
        BigDecimal totalAmount,
        @NotNull(message = "Payment Method can not be null,should be precised")
        PaymentMethod payment_method


        ) {
}
