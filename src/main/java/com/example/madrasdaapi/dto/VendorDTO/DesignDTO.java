package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.models.Design;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link Design} entity
 */
@Getter
@Setter
public class DesignDTO implements Serializable {
     private Long id;
     private String imgUrl;
     private String designType;
     private String theme;
     private String additionalInformation;

}