package com.example.madrasdaapi.controllers.webhooks;

import com.example.madrasdaapi.dto.RazorPayDTO.PaymentRequest;
import com.example.madrasdaapi.dto.ShipRocketDTO.TrackingData;
import com.example.madrasdaapi.repositories.TransactionRepository;
import com.example.madrasdaapi.services.commons.TransactionService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/webhook/")
public class WebhookController {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @PostMapping("updateTransactionStatus")
    public void updateTransactionStatus(@RequestBody PaymentRequest result) throws RazorpayException, IOException {
        transactionService.updateTransactionStatus(result);
    }

    @PostMapping("updateShipmentStatus")
    public void updateShipmentStatus(@RequestBody TrackingData trackingDetails) {
        transactionService.updateShipmentStatus(trackingDetails);
    }
}
