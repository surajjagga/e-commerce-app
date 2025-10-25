package com.example.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(

        String id,
        @NotNull(message = "FirstName is Required")
        String firstname,
        @NotNull(message = "LastName is Required")
        String lastname,
        @NotNull(message = "Email is Required")
        @Email(message = "Email should be valid")
        String email
) {}
