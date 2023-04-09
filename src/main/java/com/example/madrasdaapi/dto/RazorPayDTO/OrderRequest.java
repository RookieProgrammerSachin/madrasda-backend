package com.example.madrasdaapi.dto.RazorPayDTO;

import lombok.Data;

@Data
public class OrderRequest {
    String customerName;
    String email;
    String phoneNumber;
    Long amount;
}