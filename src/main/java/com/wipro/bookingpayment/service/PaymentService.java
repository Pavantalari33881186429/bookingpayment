package com.wipro.bookingpayment.service;

import com.wipro.bookingpayment.entity.Payment;

public interface PaymentService {
    Payment savePayment(Payment payment);
    Payment getPaymentById(Long id);
}
