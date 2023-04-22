package com.example.madrasdaapi.models.ShiprocketModels.RecommendedCourier;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@lombok.Data

public class Data{

	@JsonProperty("child_courier_id")
	private Object childCourierId;

	@JsonProperty("recommended_courier_company_id")
	private Integer recommendedCourierCompanyId;

	@JsonProperty("shiprocket_recommended_courier_id")
	private Integer shiprocketRecommendedCourierId;

	@JsonProperty("recommendation_advance_rule")
	private Integer recommendationAdvanceRule;

	@JsonProperty("recommended_by")
	private RecommendedBy recommendedBy;

	@JsonProperty("available_courier_companies")
	private List<AvailableCourierCompany> availableCourierCompanies;

	@JsonProperty("is_recommendation_enabled")
	private Integer isRecommendationEnabled;
}