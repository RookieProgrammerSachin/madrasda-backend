package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentLinkEntity {

	@JsonProperty("cancelled_at")
	private Integer cancelledAt;

	@JsonProperty("reminders")
	private Object reminders;

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("amount_paid")
	private Integer amountPaid;

	@JsonProperty("notes")
	private Object notes;

	@JsonProperty("reference_id")
	private String referenceId;

	@JsonProperty("created_at")
	private Integer createdAt;

	@JsonProperty("description")
	private String description;

	@JsonProperty("expired_at")
	private Integer expiredAt;

	@JsonProperty("reminder_enable")
	private Boolean reminderEnable;

	@JsonProperty("notify")
	private Notify notify;

	@JsonProperty("short_url")
	private String shortUrl;

	@JsonProperty("updated_at")
	private Integer updatedAt;

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("upi_link")
	private Boolean upiLink;

	@JsonProperty("accept_partial")
	private Boolean acceptPartial;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("id")
	private String id;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("expire_by")
	private Integer expireBy;

	@JsonProperty("customer")
	private Object customer;

	@JsonProperty("first_min_partial_amount")
	private Integer firstMinPartialAmount;

	@JsonProperty("status")
	private String status;

	@JsonProperty("razorpay_signature")
	private String signature;
}