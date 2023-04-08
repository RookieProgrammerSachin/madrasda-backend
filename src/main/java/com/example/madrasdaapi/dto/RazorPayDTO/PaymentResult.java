package com.example.madrasdaapi.dto.RazorPayDTO;

import lombok.Data;

@Data
public class PaymentResult{
	private Integer amount;
	private String paymentId;
	private Integer createdAt;
	private String currency;
	private String event;
	private String orderId;
	private String entity;
	private String status;
	private Integer attempts;
}