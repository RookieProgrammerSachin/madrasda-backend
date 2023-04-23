package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class AcquirerData{

	@JsonProperty("bank_transaction_id")
	private String bankTransactionId;
}