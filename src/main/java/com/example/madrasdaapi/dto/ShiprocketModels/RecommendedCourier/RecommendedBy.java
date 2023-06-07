package com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendedBy{

	@JsonProperty("id")
	private int id;

	@JsonProperty("title")
	private String title;
}