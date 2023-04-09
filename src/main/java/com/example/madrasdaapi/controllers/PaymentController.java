package com.example.madrasdaapi.controllers;

import com.example.madrasdaapi.dto.RazorPayDTO.OrderResponse;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.mappers.CustomerMapper;
import com.example.madrasdaapi.mappers.TransactionMapper;
import com.example.madrasdaapi.repositories.TransactionRepository;
import com.example.madrasdaapi.services.commons.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public OrderResponse createOrder(@RequestBody TransactionDTO orderRequest) {
        return transactionService.initiateTransaction(orderRequest);

    }
//    @PostMapping("buyNow")
//    public OrderResponse buyNow(@RequestBody TransactionDTO transactionDTO) {
//        return transactionService.initiateTransaction(transactionDTO);
//    }

}
