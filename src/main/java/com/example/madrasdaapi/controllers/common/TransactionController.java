package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.dto.ShipmentDTO;
import com.example.madrasdaapi.dto.TrackingDataDTO.TrackingData;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.models.Shipment;
import com.example.madrasdaapi.services.commons.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction/")
@RequiredArgsConstructor
public class TransactionController {
     private final TransactionService transactionService;

     @PostMapping("buyNow")
     public TransactionDTO buyNow(@RequestBody TransactionDTO transactionDTO) {
          return transactionService.initiateTransaction(transactionDTO);
     }

     @GetMapping("getOrderDetails/{transactionId}")
     public ShipmentDTO getOrderDetails(@PathVariable Long transactionId) {
          return transactionService.getOrderDetails(transactionId);
     }
     @PostMapping("updateShipment")
     public void updateStatus(@RequestBody TrackingData trackingDetails) {

          transactionService.updateStatus(trackingDetails);

     }

     @GetMapping("getAllOrdersById/{id}")
     public List<TransactionDTO> getHistoryOfOrders(@PathVariable Long id) {
          return transactionService.getHistoryOfOrdersById(id);
     }
}
