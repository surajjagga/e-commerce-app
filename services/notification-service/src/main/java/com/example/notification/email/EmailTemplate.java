package com.example.notification.email;

import lombok.Getter;

public enum EmailTemplate {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment Successfully Processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order Successfully Placed");

    @Getter
    private final String templateName;
    @Getter
    private final String Subject;


    private EmailTemplate(String templateName, String Subject) {
        this.templateName = templateName;
        this.Subject = Subject;
    }

}
