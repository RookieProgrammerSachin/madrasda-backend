package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class Order{

	@JsonProperty("entity")
	private OrderEntity entity;
}