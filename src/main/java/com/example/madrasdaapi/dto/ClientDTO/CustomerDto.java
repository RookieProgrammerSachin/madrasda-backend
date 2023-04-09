package com.example.madrasdaapi.dto.ClientDTO;

import com.example.madrasdaapi.models.Customer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link Customer} entity
 */
@Getter
@Setter
public class CustomerDto implements Serializable {
    private Long id;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;
    private String email;
}