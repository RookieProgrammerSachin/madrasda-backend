package com.example.madrasdaapi.dto.RazorPayDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity{

	@JsonProperty("notes")
	private Object notes;

	@JsonProperty("fee")
	private Object fee;

	@JsonProperty("description")
	private Object description;

	@JsonProperty("created_at")
	private Integer createdAt;

	@JsonProperty("amount_refunded")
	private Integer amountRefunded;

	@JsonProperty("bank")
	private Object bank;

	@JsonProperty("error_reason")
	private String errorReason;

	@JsonProperty("error_description")
	private String errorDescription;

	@JsonProperty("acquirer_data")
	private AcquirerData acquirerData;

	@JsonProperty("captured")
	private Boolean captured;

	@JsonProperty("contact")
	private String contact;

	@JsonProperty("invoice_id")
	private String invoiceId;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("id")
	private String id;

	@JsonProperty("international")
	private Boolean international;

	@JsonProperty("email")
	private String email;

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("refund_status")
	private Object refundStatus;

	@JsonProperty("wallet")
	private Object wallet;

	@JsonProperty("method")
	private String method;

	@JsonProperty("vpa")
	private Object vpa;

	@JsonProperty("error_source")
	private String errorSource;

	@JsonProperty("error_step")
	private String errorStep;

	@JsonProperty("tax")
	private Object tax;

	@JsonProperty("card_id")
	private String cardId;

	@JsonProperty("error_code")
	private String errorCode;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("entity")
	private String entity;

	@JsonProperty("card")
	private Card card;

	@JsonProperty("status")
	private String status;
}