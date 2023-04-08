package com.example.madrasdaapi.controllers.webhooks;

import com.example.madrasdaapi.dto.RazorPayDTO.PaymentResult;
import com.example.madrasdaapi.dto.TrackingDataDTO.TrackingData;
import com.example.madrasdaapi.repositories.TransactionRepository;
import com.example.madrasdaapi.services.commons.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/webhook/")
public class WebhookController {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @PostMapping("updateTransactionStatus")
    public void updateTransactionStatus(PaymentResult result) {
        transactionService.updateTransactionStatus(result);
    }

    @PostMapping("updateShipmentStatus")
    public void updateShipmentStatus(@RequestBody TrackingData trackingDetails) {

        transactionService.updateShipmentStatus(trackingDetails);

    }
}
