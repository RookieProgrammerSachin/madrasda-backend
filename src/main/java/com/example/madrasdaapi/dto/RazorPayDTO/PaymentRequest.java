package com.example.madrasdaapi.dto.RazorPayDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentRequest{

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

	@JsonProperty("entity")
	private String entity;
}