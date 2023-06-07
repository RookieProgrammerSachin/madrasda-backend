package com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceableCourierData{

	@JsonProperty("data")
	private Data data;

	@JsonProperty("is_zone_from_mongo")
	private boolean isZoneFromMongo;

	@JsonProperty("covid_zones")
	private CovidZones covidZones;

	@JsonProperty("seller_address")
	private List<Object> sellerAddress;

	@JsonProperty("user_insurance_manadatory")
	private boolean userInsuranceManadatory;

	@JsonProperty("is_latlong")
	private int isLatlong;

	@JsonProperty("is_old_zone_opted")
	private boolean isOldZoneOpted;

	@JsonProperty("dg_courier")
	private int dgCourier;

	@JsonProperty("eligible_for_insurance")
	private int eligibleForInsurance;

	@JsonProperty("insurace_opted_at_order_creation")
	private boolean insuraceOptedAtOrderCreation;

	@JsonProperty("on_new_zone")
	private int onNewZone;

	@JsonProperty("label_generate_type")
	private int labelGenerateType;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("company_auto_shipment_insurance_setting")
	private boolean companyAutoShipmentInsuranceSetting;

	@JsonProperty("is_allow_templatized_pricing")
	private boolean isAllowTemplatizedPricing;

	@JsonProperty("status")
	private int status;
}