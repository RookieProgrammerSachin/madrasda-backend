package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.models.ShipmentTrackActivity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Transaction} entity
 */
@Getter
@Setter
public class TransactionDTO implements Serializable {
     private Long id;
     private LocalDate orderDate;
     private LocalDate expectedDate;
     private Integer orderTotal;
     private Byte delivered;
     private Byte transactionSuccess;
     private Set<OrderItemDTO> orderItems;
     private List<ShipmentTrackActivity> shipmentActivity;
}/*
@Service
public class PaymentService {

     @Autowired
     private TransactionService transactionService;

     @Autowired
     private ShiprocketService shiprocketService;

     public void handlePayment(PaymentResult result) {
          if (result.getStatus().equals("captured")) {
               // Payment successful, update transaction status
               transactionService.updateTransactionStatus(result);

               // Create shipment details
               TrackingData trackingData = createShipmentDetails(result);

               // Place order in Shiprocket
               shiprocketService.placeOrder(trackingData);
          } else {
               // Payment failed, do nothing
               return;
          }
     }

     private TrackingData createShipmentDetails(PaymentResult result) {
          TransactionDTO transactionDTO = transactionService.getTransactionById(Long.parseLong(result.getOrderId()));

          // Set shipment details
          TrackingData trackingData = new TrackingData();
          trackingData.setOrderId(result.getOrderId());
          trackingData.setAwb(transactionDTO.getShipment().getAwb());
          trackingData.setCourierName(transactionDTO.getShipment().getCourierName());
          trackingData.setChannelOrderId(transactionDTO.getShipment().getChannelOrderId());
          trackingData.setChannel(transactionDTO.getShipment().getChannel());
          trackingData.setCurrentStatus(ShipmentStatus.Order_Placed.name());
          trackingData.setCurrentStatusId(ShipmentStatus.Order_Placed.getStatusId());
          trackingData.setShipmentStatus(ShipmentStatus.Order_Placed.name());
          trackingData.setShipmentStatusId(ShipmentStatus.Order_Placed.getStatusId());
          trackingData.setEtd(transactionDTO.getExpectedDate().toString());

          return trackingData;
     }

}

@Service
public class TransactionService {

     @Autowired
     private TransactionRepository transactionRepository;

     @Autowired
     private TransactionMapper transactionMapper;

     public void updateTransactionStatus(PaymentResult result) {
          TransactionDTO transactionDTO = getTransactionById(Long.parseLong(result.getOrderId()));
          transactionDTO.setTransactionSuccess((byte) 1);
          transactionDTO.setStatus(result.getStatus());
          saveTransaction(transactionDTO);
     }

     public TransactionDTO getTransactionById(Long id) {
          return transactionMapper.mapToDTO(transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", id)));
     }

     public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
          Transaction transaction = transactionMapper.mapToEntity(transactionDTO);
          return transactionMapper.mapToDTO(transactionRepository.save(transaction));
     }

}
*/
