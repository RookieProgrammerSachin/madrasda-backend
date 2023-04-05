package com.example.madrasdaapi.models.ShipmentTracking;

import lombok.Data;

import java.util.List;

@Data
public class TrackingData{
	private List<ShipmentTrackItem> shipmentTrack;
	private String etd;
	private List<ShipmentTrackActivitiesItem> shipmentTrackActivities;
	private Integer trackStatus;
	private String trackUrl;
	private Integer shipmentStatus;
}