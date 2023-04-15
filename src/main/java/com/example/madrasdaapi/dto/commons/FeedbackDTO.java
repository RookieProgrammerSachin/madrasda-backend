package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Feedback} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO implements Serializable {
     private Long id;
     private String query;
     private Boolean resolution;
     private Date timestamp;
     private String vendorName;
     private String email;

}