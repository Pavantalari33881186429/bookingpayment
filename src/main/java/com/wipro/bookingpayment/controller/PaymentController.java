package com.wipro.bookingpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.bookingpayment.entity.Payment;
import com.wipro.bookingpayment.service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }
}
