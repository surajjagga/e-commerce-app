package com.example.payment.service;

import com.example.payment.data.PaymentRepository;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    public Integer createPayment(PaymentRequest paymentRequest){
        var payment = paymentMapper.toPayment(paymentRequest);
        paymentRepository.save(payment);
        return null;
    }
}
