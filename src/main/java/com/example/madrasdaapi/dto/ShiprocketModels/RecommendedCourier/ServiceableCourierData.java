package com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class ServiceableCourierData{

	@JsonProperty("data")
	private Data data;

	@JsonProperty("is_zone_from_mongo")
	private Boolean isZoneFromMongo;

	@JsonProperty("covid_zones")
	private CovidZones covidZones;

	@JsonProperty("seller_address")
	private List<Object> sellerAddress;

	@JsonProperty("user_insurance_manadatory")
	private Boolean userInsuranceManadatory;

	@JsonProperty("is_latlong")
	private Integer isLatlong;

	@JsonProperty("is_old_zone_opted")
	private Boolean isOldZoneOpted;

	@JsonProperty("dg_courier")
	private Integer dgCourier;

	@JsonProperty("eligible_for_insurance")
	private Integer eligibleForInsurance;

	@JsonProperty("insurace_opted_at_order_creation")
	private Boolean insuraceOptedAtOrderCreation;

	@JsonProperty("on_new_zone")
	private Integer onNewZone;

	@JsonProperty("label_generate_type")
	private Integer labelGenerateType;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("company_auto_shipment_insurance_setting")
	private Boolean companyAutoShipmentInsuranceSetting;

	@JsonProperty("is_allow_templatized_pricing")
	private Boolean isAllowTemplatizedPricing;

	@JsonProperty("status")
	private Integer status;
}