package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentLink{

	@JsonProperty("entity")
	private PaymentLinkEntity entity;
}