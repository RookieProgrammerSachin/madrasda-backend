package com.example.madrasdaapi.models;

import com.example.madrasdaapi.dto.ShipmentDTO;
import com.example.madrasdaapi.dto.commons.OrderItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link Transaction} entity
 */
@Getter
@Setter
public class TransactionDTO implements Serializable {
    private Long id;
    private Date orderDate;
    private Integer orderTotal;
    private Integer paymentId;
    private CustomerDTO customer;
    private String status;
    private Set<OrderItemDTO> orderItems = new HashSet<>();
    private List<ShipmentTrackActivity> shipmentActivity;

}