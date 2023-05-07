package com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@lombok.Data

public class RecommendedBy{

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("title")
	private String title;
}