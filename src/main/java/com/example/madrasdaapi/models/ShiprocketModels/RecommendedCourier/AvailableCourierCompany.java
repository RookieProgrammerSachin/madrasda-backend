package com.example.madrasdaapi.models.ShiprocketModels.RecommendedCourier;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@lombok.Data
public class AvailableCourierCompany{

    @JsonProperty("is_rto_address_available")
    private Boolean isRtoAddressAvailable;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("air_max_weight")
    private String airMaxWeight;

    @JsonProperty("qc_courier")
    private Integer qcCourier;

    @JsonProperty("rto_charges")
    private Double rtoCharges;

    @JsonProperty("coverage_charges")
    private Integer coverageCharges;

    @JsonProperty("base_weight")
    private String baseWeight;

    @JsonProperty("mode")
    private Integer mode;

    @JsonProperty("pickup_performance")
    private Double pickupPerformance;

    @JsonProperty("realtime_tracking")
    private String realtimeTracking;

    @JsonProperty("is_custom_rate")
    private Integer isCustomRate;

    @JsonProperty("pickup_supress_hours")
    private Integer pickupSupressHours;

    @JsonProperty("etd")
    private String etd;

    @JsonProperty("zone")
    private String zone;

    @JsonProperty("rank")
    private String rank;

    @JsonProperty("id")
    private Integer id;

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
    private Boolean isHyperlocal;

    @JsonProperty("entry_tax")
    private Integer entryTax;

    @JsonProperty("charge_weight")
    private Integer chargeWeight;

    @JsonProperty("surface_max_weight")
    private String surfaceMaxWeight;

    @JsonProperty("delivery_performance")
    private Double deliveryPerformance;

    @JsonProperty("metro")
    private Integer metro;

    @JsonProperty("pickup_availability")
    private String pickupAvailability;

    @JsonProperty("region")
    private Integer region;

    @JsonProperty("weight_cases")
    private Double weightCases;

    @JsonProperty("cutoff_time")
    private String cutoffTime;

    @JsonProperty("city")
    private String city;

    @JsonProperty("description")
    private String description;

    @JsonProperty("odablock")
    private Boolean odablock;

    @JsonProperty("suppress_text")
    private String suppressText;

    @JsonProperty("call_before_delivery")
    private String callBeforeDelivery;

    @JsonProperty("courier_name")
    private String courierName;

    @JsonProperty("blocked")
    private Integer blocked;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("assured_amount")
    private Integer assuredAmount;

    @JsonProperty("base_courier_id")
    private Object baseCourierId;

    @JsonProperty("cod_multiplier")
    private Integer codMultiplier;

    @JsonProperty("other_charges")
    private Integer otherCharges;

    @JsonProperty("cod_charges")
    private Integer codCharges;

    @JsonProperty("min_weight")
    private Integer minWeight;

    @JsonProperty("courier_company_id")
    private Integer courierCompanyId;

    @JsonProperty("cost")
    private String cost;

    @JsonProperty("etd_hours")
    private Integer etdHours;

    @JsonProperty("suppress_date")
    private String suppressDate;

    @JsonProperty("ship_type")
    private Integer shipType;

    @JsonProperty("suppression_dates")
    private List<Object> suppressionDates;

    @JsonProperty("rto_performance")
    private Double rtoPerformance;

    @JsonProperty("is_surface")
    private Boolean isSurface;

    @JsonProperty("local_region")
    private Integer localRegion;

    @JsonProperty("pickup_priority")
    private String pickupPriority;

    @JsonProperty("freight_charge")
    private Double freightCharge;

    @JsonProperty("delivery_boy_contact")
    private String deliveryBoyContact;

    @JsonProperty("seconds_left_for_pickup")
    private Integer secondsLeftForPickup;

    @JsonProperty("cod")
    private Integer cod;

    @JsonProperty("volumetric_max_weight")
    private Integer volumetricMaxWeight;

    @JsonProperty("is_international")
    private Integer isInternational;

    @JsonProperty("pod_available")
    private String podAvailable;

    @JsonProperty("tracking_performance")
    private Double trackingPerformance;
}