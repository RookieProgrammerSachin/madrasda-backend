package com.example.madrasdaapi.dto.RazorPayDTO.PaymentRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcquirerData{

	@JsonProperty("auth_code")
	private Object authCode;
}