package com.example.madrasdaapi.models.ShipmentTracking;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrackingData{
	private List<ShipmentTrackItem> shipmentTrack = new ArrayList<>();;
	private String etd;
	private List<ShipmentTrackActivitiesItem> shipmentTrackActivities = new ArrayList<>();
	private Integer trackStatus;
	private String trackUrl;
	private Integer shipmentStatus;
}