package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipmentDTO;
import com.example.madrasdaapi.dto.commons.CancelRequestDTO;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.models.Transaction;
import com.example.madrasdaapi.services.commons.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

     @GetMapping("/manageOrders")
     public Page<TransactionDTO> viewAllOrders(@RequestParam(defaultValue = "0") int pageNo,
                                               @RequestParam(defaultValue = "10") int pageSize) {
          return transactionService.getAllOrders(pageNo, pageSize);
     }

     @ResponseStatus(HttpStatus.OK)
     @PutMapping("cancelOrder")
     public void cancelOrder(@RequestBody CancelRequestDTO cancelRequestDTO) {
          transactionService.cancelOrder(cancelRequestDTO);
     }

     @GetMapping("getAllCancelRequests")
     public Page<CancelRequestDTO> getAllCancelRequests(@RequestParam(defaultValue = "0") int pageNo,
                                                   @RequestParam(defaultValue = "10") int pageSize) {
          return transactionService.getAllCancelRequests(pageNo, pageSize);
     }

}
