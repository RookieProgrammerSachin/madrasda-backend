package com.example.madrasdaapi.dto.ShipRocketDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TrackingData{
	@JsonProperty("awb")
	private Long awb;

	@JsonProperty("current_status")
	private String currentStatus;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("current_timestamp")
	private String currentTimestamp;

	@JsonProperty("etd")
	private String etd;

	@JsonProperty("current_status_id")
	private Integer currentStatusId;

	@JsonProperty("shipment_status")
	private String shipmentStatus;

	@JsonProperty("shipment_status_id")
	private Integer shipmentStatusId;

	@JsonProperty("channel_order_id")
	private String channelOrderId;

	@JsonProperty("channel")
	private String channel;

	@JsonProperty("courier_name")
	private String courierName;

	@JsonProperty("scans")
	private List<ScansItem> scans;

}