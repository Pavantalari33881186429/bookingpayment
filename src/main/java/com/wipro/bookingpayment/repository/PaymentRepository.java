package com.wipro.bookingpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bookingpayment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
