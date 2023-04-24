package com.example.madrasdaapi.dto.RazorPayDTO.PaymentRequest;

import lombok.Data;

@Data
public class OrderRequest {
    String customerName;
    String email;
    String phoneNumber;
    Long amount;
}