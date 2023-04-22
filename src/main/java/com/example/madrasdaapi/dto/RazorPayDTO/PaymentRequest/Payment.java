package com.example.madrasdaapi.dto.RazorPayDTO.PaymentRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment{

	@JsonProperty("entity")
	private Entity entity;
}