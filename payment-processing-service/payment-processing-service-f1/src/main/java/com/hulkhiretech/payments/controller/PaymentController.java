package com.hulkhiretech.payments.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.pojo.InitiateTxnRequest;
import com.hulkhiretech.payments.pojo.PaymentResponse;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payments")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;

	@PostMapping
	public CreateTxnResponse createPayment(@RequestBody CreateTxnRequest createTxnRequest) {
		log.info("Received payment request| createTxnRequest:{}", 
				createTxnRequest);
		
		CreateTxnResponse response = paymentService.createPayment(createTxnRequest);
		log.info("Payment service response: {}", response);
		
		
		return response;
	}
	
	@PostMapping("/{txnReference}/initiate")
	public PaymentResponse initiatePayment(@PathVariable String txnReference, @RequestBody InitiateTxnRequest initiatePaymentRequest) {
		log.info("Initiating payment for transaction reference: {}", txnReference);
		log.info("Initiate payment request: {}", initiatePaymentRequest);
		
		PaymentResponse response=paymentService.initiatePayment(txnReference, initiatePaymentRequest);
		log.info("Payment initiation response: {}", response);
		return response;
	}
}
