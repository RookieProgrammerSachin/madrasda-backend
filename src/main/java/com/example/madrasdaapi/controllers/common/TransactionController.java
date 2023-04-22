package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipmentDTO;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.services.commons.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction/")
@RequiredArgsConstructor
@CrossOrigin
public class TransactionController {
     private final TransactionService transactionService;



     @GetMapping("getOrderDetails/{transactionId}")
     public ShipmentDTO getOrderDetails(@PathVariable Long transactionId) {
          return transactionService.getOrderDetails(transactionId);
     }


     @GetMapping("getAllOrdersById/")
     public List<TransactionDTO> getHistoryOfOrders() {
          return transactionService.getHistoryOfOrdersByCustomerId(AuthContext.getCurrentUser());
     }
}
