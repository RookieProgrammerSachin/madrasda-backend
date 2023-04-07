package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.models.ShipmentTrackActivity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Transaction} entity
 */
@Getter
@Setter
public class TransactionDTO implements Serializable {
     private Long id;
     private LocalDate orderDate;
     private LocalDate expectedDate;
     private Integer orderTotal;
     private Byte delivered;
     private Byte transactionSuccess;
     private Set<OrderItemDTO> orderItems;
     private String status;
     private List<ShipmentTrackActivity> shipmentActivity;
}