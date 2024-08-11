package com.wipro.bookingpayment.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.bookingpayment.entity.Payment;
import com.wipro.bookingpayment.service.PaymentService;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    public void testCreatePayment() throws Exception {
        Payment payment = new Payment(1L,1L , "Success", 100.0, "CreditCard");
        when(paymentService.savePayment(payment)).thenReturn(payment);

        mockMvc.perform(post("/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(payment)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPaymentById() throws Exception {
        Payment payment = new Payment(1L, 1L, "Success", 100.0, "CreditCard");
        when(paymentService.getPaymentById(1L)).thenReturn(payment);

        mockMvc.perform(get("/payment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
