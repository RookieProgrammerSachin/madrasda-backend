package com.example.madrasdaapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Shipment} entity
 */
@Getter
@Setter
public class ShipmentDTO implements Serializable {
    private Long awb;
    private String currentStatus;
    private String currentTimestamp;
    private String etd;
    private String shipmentStatus;
    private String channel;
    private String courierName;
    private List<ShipmentTrackActivityDTO> scans = new ArrayList<>();
}