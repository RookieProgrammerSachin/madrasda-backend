package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class PaymentEntity {

	@JsonProperty("notes")
	private List<Object> notes;

	@JsonProperty("fee")
	private Integer fee;

	@JsonProperty("created_at")
	private Integer createdAt;

	@JsonProperty("description")
	private String description;

	@JsonProperty("amount_refunded")
	private Integer amountRefunded;

	@JsonProperty("bank")
	private String bank;

	@JsonProperty("error_reason")
	private Object errorReason;

	@JsonProperty("acquirer_data")
	private AcquirerData acquirerData;

	@JsonProperty("error_description")
	private Object errorDescription;

	@JsonProperty("captured")
	private Boolean captured;

	@JsonProperty("contact")
	private String contact;

	@JsonProperty("base_amount")
	private Integer baseAmount;

	@JsonProperty("invoice_id")
	private Object invoiceId;

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

	@JsonProperty("amount_transferred")
	private Integer amountTransferred;

	@JsonProperty("refund_status")
	private Object refundStatus;

	@JsonProperty("wallet")
	private Object wallet;

	@JsonProperty("method")
	private String method;

	@JsonProperty("error_source")
	private Object errorSource;

	@JsonProperty("error_step")
	private Object errorStep;

	@JsonProperty("vpa")
	private Object vpa;

	@JsonProperty("tax")
	private Integer tax;

	@JsonProperty("card_id")
	private Object cardId;

	@JsonProperty("error_code")
	private Object errorCode;

	@JsonProperty("fee_bearer")
	private String feeBearer;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("card")
	private Object card;

	@JsonProperty("entity")
	private String entity;

	@JsonProperty("status")
	private String status;
}