package com.example.madrasdaapi.models.ShipmentTracking;

import lombok.Data;

import java.util.Date;

@Data
public class ShipmentTrackItem{
	private Integer courierCompanyId;
	private Object courierAgentDetails;
	private Date edd;
	private String deliveredTo;
	private String consigneeName;
	private String origin;
	private String destination;
	private String weight;
	private Long shipmentId;
	private Integer packages;
	private Date pickupDate;
	private String currentStatus;
	private Integer id;
	private String awbCode;
	private Long orderId;
	private Date deliveredDate;
}