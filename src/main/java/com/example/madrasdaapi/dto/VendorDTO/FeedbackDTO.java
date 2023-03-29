package com.example.madrasdaapi.dto.VendorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Feedback} entity
 */
@Data
public class FeedbackDTO implements Serializable {
     private Long id;
     private String query;
     private Boolean resolution;
}