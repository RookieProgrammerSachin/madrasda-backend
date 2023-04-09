package com.example.madrasdaapi.dto.ShipRocketDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.ShipmentTrackActivity} entity
 */
@Getter
@Setter
public class ShipmentTrackActivityDTO implements Serializable {
    private Long id;
    private String date;
    private String activity;
    private String location;
}