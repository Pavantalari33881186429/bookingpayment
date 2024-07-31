package com.wipro.bookingpayment.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wipro.bookingpayment.entity.Payment;
import com.wipro.bookingpayment.repository.PaymentRepository;
import com.wipro.bookingpayment.service.PaymentService;
import com.wipro.bookingpayment.vo.PaymentMessage;
import com.wipro.bookingpayment.vo.PaymentResponse;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private KafkaTemplate<String, PaymentResponse> kafkaTemplate;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

   
}

