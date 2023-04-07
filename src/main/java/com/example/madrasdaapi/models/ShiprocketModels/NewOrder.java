package com.example.madrasdaapi.models.ShiprocketModels;

import lombok.Data;

@Data
public class NewOrder{
	private Object courierCompanyId;
	private Integer statusCode;
	private Object courierName;
	private Object awbCode;
	private Integer shipmentId;
	private Integer orderId;
	private String status;
	private Integer onboardingCompletedNow;
}