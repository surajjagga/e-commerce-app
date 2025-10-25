package com.example.notification.kafka;

import com.example.notification.data.Notification;
import com.example.notification.data.NotificationType;
import com.example.notification.email.EmailService;
import com.example.notification.kafka.order.OrderConfirmation;
import com.example.notification.kafka.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Received Payment Confirmation for payment {}", paymentConfirmation);
        notificationRepository.save(Notification.builder().notificationDate(LocalDateTime.now()).paymentConfirmation(paymentConfirmation).notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .build());
        var customerName = paymentConfirmation.customerFirstName()+" "+ paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(),customerName,paymentConfirmation.amount(),paymentConfirmation.orderReference());
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Received Payment Confirmation for Order {}", orderConfirmation);
        notificationRepository.save(Notification.builder().notificationDate(LocalDateTime.now()).orderConfirmation(orderConfirmation).notificationType(NotificationType.ORDER_CONFIRMATION)
                .build());
        var customerName = orderConfirmation.customer().firstname()+" "+ orderConfirmation.customer().lastname();
        emailService.sendOrderSuccessEmail(orderConfirmation.customer().email(),customerName,orderConfirmation.totalAmount(),orderConfirmation.orderReference(),orderConfirmation.products());
    }

}
