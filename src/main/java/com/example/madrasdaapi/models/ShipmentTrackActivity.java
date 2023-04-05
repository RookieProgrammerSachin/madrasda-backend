package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Entity
@ToString
public class ShipmentTrackActivity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private Date date;
	private String activity;
	private String srStatusLabel;
	private String location;
	private String srStatus;
	private String status;
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "shipment_id")
	private Shipment shipments;
}