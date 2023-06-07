package com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidZones{

	@JsonProperty("delivery_zone")
	private Object deliveryZone;

	@JsonProperty("pickup_zone")
	private Object pickupZone;
}