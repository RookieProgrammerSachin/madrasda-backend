package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "shipment", schema = "madrasda")
public class Shipment {
     @Id
     @Column(name = "shipment_id")
     private Long shipmentId;

     @Column(name = "awb_code")
     private String awbCode;

     @Column(name = "courier_company_id")
     private Integer courierCompanyId;

     @Column(name = "pickup_date")
     private Date pickupDate;

     @Column(name = "delivered_date")
     private Date deliveredDate;

     @Column(name = "weight")
     private Double weight;

     @Column(name = "packages")
     private Integer packages;

     @Column(name = "current_status")
     private Integer currentStatus;

     @Column(name = "delivered_to")
     private String deliveredTo;

     @Column(name = "destination")
     private String destination;

     @Column(name = "consignee_name")
     private String consigneeName;

     @Column(name = "origin")
     private String origin;

     @Column(name = "courier_agent_details")
     private String courierAgentDetails;

     @Column(name = "etd")
     private Date edd;

     @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipments")
     private List<ShipmentTrackActivity> shipmentActivities = new ArrayList<>();

     @OneToOne
     @ToString.Exclude
     @JoinColumn(name = "transaction_id")
     private Transaction transaction;
}
