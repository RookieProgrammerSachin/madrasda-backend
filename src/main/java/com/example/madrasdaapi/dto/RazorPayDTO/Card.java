package com.example.madrasdaapi.dto.RazorPayDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card{

	@JsonProperty("emi")
	private Boolean emi;

	@JsonProperty("last4")
	private String last4;

	@JsonProperty("sub_type")
	private String subType;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("international")
	private Boolean international;

	@JsonProperty("entity")
	private String entity;

	@JsonProperty("issuer")
	private Object issuer;

	@JsonProperty("network")
	private String network;
}