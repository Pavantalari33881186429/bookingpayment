package com.wipro.bookingpayment.kafka;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wipro.bookingpayment.entity.Payment;
import com.wipro.bookingpayment.serviceImpl.PaymentServiceImpl;
import com.wipro.bookingpayment.vo.PaymentMessage;
import com.wipro.bookingpayment.vo.PaymentResponse;

@Service
public class PaymentConsumer {
	private final KafkaTemplate<String, PaymentResponse> kafkaTemplate;
	
	int flag=1;
	
	@Autowired
	PaymentServiceImpl svcimpl;
	
	@Autowired
	BookingProducer producer;
	
	
	
	 public PaymentConsumer(KafkaTemplate<String, PaymentResponse> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}



	@KafkaListener(topics = "T1", groupId = "payment-group", containerFactory = "kafkaListenerContainerFactory",properties = {"spring.json.value.default.type=com.wipro.bookingpayment.vo.PaymentMessage"})
	    public void listen(PaymentMessage message) {
	        Payment payment = new Payment();
	        payment.setBookingId(message.getBookingId());
	        payment.setAmount(message.getAmount());
	        payment.setModeOfPayment(message.getModeOfPayment());
	        payment.setStatus("processing");
	        
	        try {
	        svcimpl.savePayment(payment);
	        
	        }catch(Exception e) {
                flag=0;
	        	System.out.println(e);
	        }

	        // Simulate payment processing
	        if(flag == 1) {
	        payment.setStatus("successful");
	        svcimpl.savePayment(payment);
	        }
	        else {
	        	payment.setStatus("failed");
	        	svcimpl.savePayment(payment);
	        }
	        

	        
	        PaymentResponse response = new PaymentResponse(message.getBookingId(), payment.getStatus());
	        producer.sendPaymentMessage(response);
	    }

}
