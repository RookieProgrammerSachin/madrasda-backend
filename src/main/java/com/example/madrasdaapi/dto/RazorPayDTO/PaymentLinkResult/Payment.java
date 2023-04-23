package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class Payment{

	@JsonProperty("entity")
	private PaymentEntity entity;
}