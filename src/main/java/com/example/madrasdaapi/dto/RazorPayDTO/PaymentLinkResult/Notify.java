package com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class Notify{

	@JsonProperty("whatsapp")
	private Boolean whatsapp;

	@JsonProperty("sms")
	private Boolean sms;

	@JsonProperty("email")
	private Boolean email;
}