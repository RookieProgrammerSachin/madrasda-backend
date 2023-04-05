package com.example.madrasdaapi.dto;

import com.example.madrasdaapi.dto.VendorDTO.ShipmentTrackActivitiesItemDto;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.models.ShipmentTracking.TrackingData;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link TrackingData} entity
 */
@Getter
@Setter
public class TrackingDataDTO implements Serializable {
     private Long id;
     private TransactionDTO transaction;
     private String etd;
     private List<ShipmentTrackActivitiesItemDto> shipmentTrackActivities;
     private Integer shipmentStatus;
}