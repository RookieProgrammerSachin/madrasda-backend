package com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableCourierCompaniesItem{

	@JsonProperty("is_rto_address_available")
	private boolean isRtoAddressAvailable;

	@JsonProperty("rating")
	private double rating;

	@JsonProperty("air_max_weight")
	private String airMaxWeight;

	@JsonProperty("qc_courier")
	private int qcCourier;

	@JsonProperty("rto_charges")
	private double rtoCharges;

	@JsonProperty("coverage_charges")
	private int coverageCharges;

	@JsonProperty("base_weight")
	private String baseWeight;

	@JsonProperty("mode")
	private int mode;

	@JsonProperty("pickup_performance")
	private double pickupPerformance;

	@JsonProperty("realtime_tracking")
	private String realtimeTracking;

	@JsonProperty("is_custom_rate")
	private int isCustomRate;

	@JsonProperty("pickup_supress_hours")
	private int pickupSupressHours;

	@JsonProperty("etd")
	private String etd;

	@JsonProperty("zone")
	private String zone;

	@JsonProperty("rank")
	private String rank;

	@JsonProperty("id")
	private int id;

	@JsonProperty("state")
	private String state;

	@JsonProperty("courier_type")
	private String courierType;

	@JsonProperty("others")
	private String others;

	@JsonProperty("edd")
	private String edd;

	@JsonProperty("estimated_delivery_days")
	private String estimatedDeliveryDays;

	@JsonProperty("postcode")
	private String postcode;

	@JsonProperty("is_hyperlocal")
	private boolean isHyperlocal;

	@JsonProperty("entry_tax")
	private int entryTax;

	@JsonProperty("charge_weight")
	private double chargeWeight;

	@JsonProperty("surface_max_weight")
	private String surfaceMaxWeight;

	@JsonProperty("delivery_performance")
	private double deliveryPerformance;

	@JsonProperty("metro")
	private int metro;

	@JsonProperty("pickup_availability")
	private String pickupAvailability;

	@JsonProperty("region")
	private int region;

	@JsonProperty("weight_cases")
	private double weightCases;

	@JsonProperty("cutoff_time")
	private String cutoffTime;

	@JsonProperty("city")
	private String city;

	@JsonProperty("description")
	private String description;

	@JsonProperty("odablock")
	private boolean odablock;

	@JsonProperty("suppress_text")
	private String suppressText;

	@JsonProperty("call_before_delivery")
	private String callBeforeDelivery;

	@JsonProperty("courier_name")
	private String courierName;

	@JsonProperty("blocked")
	private int blocked;

	@JsonProperty("rate")
	private double rate;

	@JsonProperty("assured_amount")
	private int assuredAmount;

	@JsonProperty("base_courier_id")
	private Object baseCourierId;

	@JsonProperty("cod_multiplier")
	private int codMultiplier;

	@JsonProperty("other_charges")
	private int otherCharges;

	@JsonProperty("cod_charges")
	private int codCharges;

	@JsonProperty("secure_shipment_disabled")
	private boolean secureShipmentDisabled;

	@JsonProperty("min_weight")
	private double minWeight;

	@JsonProperty("courier_company_id")
	private int courierCompanyId;

	@JsonProperty("cost")
	private String cost;

	@JsonProperty("etd_hours")
	private int etdHours;

	@JsonProperty("suppress_date")
	private String suppressDate;

	@JsonProperty("ship_type")
	private int shipType;

	@JsonProperty("suppression_dates")
	private List<Object> suppressionDates;

	@JsonProperty("rto_performance")
	private double rtoPerformance;

	@JsonProperty("is_surface")
	private boolean isSurface;

	@JsonProperty("local_region")
	private int localRegion;

	@JsonProperty("pickup_priority")
	private String pickupPriority;

	@JsonProperty("freight_charge")
	private double freightCharge;

	@JsonProperty("delivery_boy_contact")
	private String deliveryBoyContact;

	@JsonProperty("seconds_left_for_pickup")
	private int secondsLeftForPickup;

	@JsonProperty("cod")
	private int cod;

	@JsonProperty("volumetric_max_weight")
	private int volumetricMaxWeight;

	@JsonProperty("is_international")
	private int isInternational;

	@JsonProperty("pod_available")
	private String podAvailable;

	@JsonProperty("tracking_performance")
	private double trackingPerformance;
}