package com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data{

	@JsonProperty("child_courier_id")
	private Object childCourierId;

	@JsonProperty("recommended_courier_company_id")
	private int recommendedCourierCompanyId;

	@JsonProperty("shiprocket_recommended_courier_id")
	private int shiprocketRecommendedCourierId;

	@JsonProperty("recommendation_advance_rule")
	private int recommendationAdvanceRule;

	@JsonProperty("recommended_by")
	private RecommendedBy recommendedBy;

	@JsonProperty("available_courier_companies")
	private List<AvailableCourierCompaniesItem> availableCourierCompanies;

	@JsonProperty("is_recommendation_enabled")
	private int isRecommendationEnabled;
}