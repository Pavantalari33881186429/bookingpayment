package com.wipro.bookingpayment.kafka;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wipro.bookingpayment.vo.PaymentResponse;


@Service
public class BookingProducer {
    private final KafkaTemplate<String, PaymentResponse> kafkaTemplate;

    public BookingProducer(KafkaTemplate<String, PaymentResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

 // Send response to T2 topics
    public void sendPaymentMessage(PaymentResponse message) {
        kafkaTemplate.send("T2", message);
    }
}