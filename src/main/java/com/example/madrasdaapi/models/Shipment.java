package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shipment_tracking", indexes = {
        @Index(name = "idx_shipment_transaction_id", columnList = "transaction_id")
})
public class Shipment {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "awb")
     private Long awb;

     @Column(name = "current_status")
     private String currentStatus;

     @Column(name = "order_id")
     private String orderId;

     @Column(name = "curr_time")
     private String currentTimestamp;

     @Column(name = "etd")
     private String etd;

     @Column(name = "current_status_id")
     private Integer currentStatusId;

     @Column(name = "shipment_status")
     private String shipmentStatus;

     @Column(name = "shipment_status_id")
     private Integer shipmentStatusId;

     @Column(name = "channel_order_id")
     private String channelOrderId;

     @Column(name = "channel")
     private String channel;

     @Column(name = "courier_name")
     private String courierName;

     @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name = "tracking_id")
     private List<ShipmentTrackActivity> scans = new ArrayList<>();

     @OneToOne
     @ToString.Exclude
     @JoinColumn(referencedColumnName = "id")
     private Transaction transaction;
}
