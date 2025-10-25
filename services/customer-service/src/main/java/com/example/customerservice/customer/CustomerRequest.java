package com.example.customerservice.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(String id,
                              @NotNull(message = "Customer firstName can not be blank")
                               String firstname,
                              @NotNull(message = "Customer firstName can not be blank")
                              String lastname,
                              @NotNull(message = "Customer email can not be blank")
                              @Email(message = "Emil entered is not valid")
                              String email,
                              Address address) {


}
