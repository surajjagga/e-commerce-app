package com.example.customerservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceApplicationTests {

    @Nested
    class TestCreateCustomer {
        @Test
        @DisplayName("Test Create Customer")
        void createCustomer() {
        }
    }


}
