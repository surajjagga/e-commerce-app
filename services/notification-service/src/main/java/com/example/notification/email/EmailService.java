package com.example.notification.email;


import com.example.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String destinationEmail,
                                        String customerName, BigDecimal totalAmount, String orderReference) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        helper.setFrom("suraj.jagga@gmail.com");

        final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplateName();

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("orderReference", orderReference);
        model.put("customerName", customerName);
        model.put("totalAmount", totalAmount);

        Context context = new Context();
        context.setVariables(model);
        helper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            helper.setText(htmlTemplate, true);
            helper.setTo("suraj.jagga@gmail.com");
            mailSender.send(mimeMessage);
            log.info("Payment Confirmation Email sent successfully");
        } catch (MessagingException e) {
            log.warn("Can not send Payment email to {}", destinationEmail);
        }


    }


    @Async
    public void sendOrderSuccessEmail(String destinationEmail,
                                        String customerName, BigDecimal totalAmount, String orderReference, List<Product> products) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        helper.setFrom("suraj.jagga@gmail.com");

        final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplateName();

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("orderReference", orderReference);
        model.put("customerName", customerName);
        model.put("totalAmount", totalAmount);
        model.put("products", products);

        Context context = new Context();
        context.setVariables(model);
        helper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            helper.setText(htmlTemplate, true);
            helper.setTo("suraj.jagga@gmail.com");
            mailSender.send(mimeMessage);
            log.info("Order Confirmation Email sent successfully");
        } catch (MessagingException e) {
            log.warn("Can not send Order email to {}", destinationEmail);
        }



    }
}
