package com.example.madrasdaapi.models.ShiprocketModels.OrderDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShiprocketOrderDetail{

	@JsonProperty("courier_company_id")
	private String courierCompanyId;

	@JsonProperty("status_code")
	private Integer statusCode;

	@JsonProperty("courier_name")
	private String courierName;

	@JsonProperty("awb_code")
	private String awbCode;

	@JsonProperty("shipment_id")
	private Integer shipmentId;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("status")
	private String status;

	@JsonProperty("onboarding_completed_now")
	private Boolean onboardingCompletedNow;
}