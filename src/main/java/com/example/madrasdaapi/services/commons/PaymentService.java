package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.models.PayoutRecord;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.PayoutRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PayoutRepository payoutRepository;
    private final VendorRepository vendorRepository;

    public void requestPayout(String email) {
        Vendor vendor = vendorRepository.findByUser_Email(email);
        if(vendor.getOutstandingProfit().longValue() <=  0 )
            throw new APIException("No outstanding profits", HttpStatus.BAD_REQUEST);
        PayoutRecord payoutRecord = new PayoutRecord();
        payoutRecord.setPaid(false);
        payoutRecord.setVendor(vendor);
        payoutRecord.setAmount(vendor.getOutstandingProfit());

        payoutRepository.save(payoutRecord);
    }

    public void togglePayout(Long id) {
        PayoutRecord payoutRecord = payoutRepository.findById(id).get();
        payoutRecord.setDatePaid(new Date());
        payoutRecord.setPaid(true);
        payoutRepository.save(payoutRecord);

    }
}
