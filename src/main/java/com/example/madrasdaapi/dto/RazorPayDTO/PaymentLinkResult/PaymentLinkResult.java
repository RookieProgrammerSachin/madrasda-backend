package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class PaymentLinkResult{

	@JsonProperty("contains")
	private List<String> contains;

	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("created_at")
	private Integer createdAt;

	@JsonProperty("event")
	private String event;

	@JsonProperty("razorpay_signature")
	private String signature;

	@JsonProperty("entity")
	private String entity;
}