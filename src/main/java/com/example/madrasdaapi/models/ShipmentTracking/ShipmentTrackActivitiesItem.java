package com.example.madrasdaapi.models.ShipmentTracking;

import lombok.Data;

import java.util.Date;

@Data
public class ShipmentTrackActivitiesItem{
	private Date date;
	private String activity;
	private String location;
	private String srStatus;
	private String status;
}