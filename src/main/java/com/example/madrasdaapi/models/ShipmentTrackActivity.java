package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "shipment_scan")
@ToString
public class ShipmentTrackActivity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	private String date;

	@Column(name = "activity")
	private String activity;

	@Column(name = "location")
	private String location;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tracking_id")
	@ToString.Exclude
	private Shipment shipment;
}