package com.wipro.bookingpayment.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import com.wipro.bookingpayment.entity.Payment;
import com.wipro.bookingpayment.repository.PaymentRepository;
import com.wipro.bookingpayment.vo.PaymentResponse;

public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private KafkaTemplate<String, PaymentResponse> kafkaTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePayment() {
        Payment payment = new Payment(1L, 1L, "Success", 100.0, "CreditCard");
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentServiceImpl.savePayment(payment);
        assertEquals(payment.getPaymentId(), savedPayment.getPaymentId());
    }

    @Test
    public void testGetPaymentById() {
        Payment payment = new Payment(1L, 1L, "Success", 100.0, "CreditCard");
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        Payment fetchedPayment = paymentServiceImpl.getPaymentById(1L);
        assertEquals(payment.getPaymentId(), fetchedPayment.getPaymentId());
    }
}
