package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class OrderEntity {
	@JsonProperty("razorpay_signature")
	private String signature;

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("amount_paid")
	private Integer amountPaid;

	@JsonProperty("notes")
	private List<Object> notes;

	@JsonProperty("created_at")
	private Integer createdAt;

	@JsonProperty("amount_due")
	private Integer amountDue;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("receipt")
	private Object receipt;

	@JsonProperty("id")
	private String id;

	@JsonProperty("entity")
	private String entity;

	@JsonProperty("offer_id")
	private Object offerId;

	@JsonProperty("attempts")
	private Integer attempts;

	@JsonProperty("status")
	private String status;
}