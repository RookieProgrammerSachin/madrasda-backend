package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.mappers.CustomerMapper;
import com.example.madrasdaapi.mappers.TransactionMapper;
import com.example.madrasdaapi.repositories.TransactionRepository;
import com.example.madrasdaapi.services.commons.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {
    private final CustomerMapper customerMapper;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody TransactionDTO orderRequest) {
        return transactionService.initiateTransaction(orderRequest);

    }

    @GetMapping("/getShippingCharges/{pincode}")
    public Double calculateShippingCharges(@PathVariable String pincode) throws IOException {
        return transactionService.calculateShippingCharges(pincode, true);
    }
}
