package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class Payload{

	@JsonProperty("payment")
	private Payment payment;

	@JsonProperty("payment_link")
	private PaymentLink paymentLink;

	@JsonProperty("order")
	private Order order;
}