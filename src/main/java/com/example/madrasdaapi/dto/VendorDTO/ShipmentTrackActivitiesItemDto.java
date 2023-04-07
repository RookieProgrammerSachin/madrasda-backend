package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.models.ShipmentTrackActivity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link ShipmentTrackActivity} entity
 */
@Getter
@Setter
public class ShipmentTrackActivitiesItemDto implements Serializable {
     private Long id;
     private String date;
     private String activity;
     private String srStatusLabel;
     private String location;
     private String srStatus;
     private String status;
}