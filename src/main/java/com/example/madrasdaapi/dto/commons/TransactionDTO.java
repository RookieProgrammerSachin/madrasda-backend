package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.dto.ClientDTO.CustomerDto;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipmentTrackActivityDTO;
import com.example.madrasdaapi.models.ShipmentTrackActivity;
import com.example.madrasdaapi.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private BigDecimal orderTotal;
    private String paymentId;
    private UserDTO billingCustomer;
    private Boolean billingIsShipping;
    private CustomerDto shippingAddress;
    private String status;
    private String orderId;
    private Boolean cancelRequested;
    private Boolean cancelled;
    private Set<OrderItemDTO> orderItems = new HashSet<>();
    private List<ShipmentTrackActivityDTO> shipmentActivity;

}