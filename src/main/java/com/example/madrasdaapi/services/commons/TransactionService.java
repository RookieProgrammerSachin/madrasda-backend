package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.mappers.TransactionMapper;
import com.example.madrasdaapi.models.Shipment;
import com.example.madrasdaapi.models.ShipmentStatus;
import com.example.madrasdaapi.models.ShipmentTracking.TrackingData;
import com.example.madrasdaapi.models.Transaction;
import com.example.madrasdaapi.repositories.OrderRepository;
import com.example.madrasdaapi.repositories.TransactionRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
     private final TransactionRepository transactionRepository;
     private final OrderRepository orderRepository;
     private final TransactionMapper transactionMapper;
     private final UserRepository userRepository;

     public TransactionDTO initiateTransaction(TransactionDTO transactionDTO) {
          Transaction transaction = transactionMapper.mapToEntity(transactionDTO);
          Shipment shipmentDetails = new Shipment();
          shipmentDetails.setCurrentStatus(ShipmentStatus.Order_Placed.ordinal());
          transaction.setShipment(shipmentDetails);
          return transactionMapper.mapToDTO(transactionRepository.save(transaction));

     }

     public void updateStatus(TrackingData trackingData) {
          Transaction transaction = transactionRepository.findById(trackingData.getShipmentTrack().get(0).getOrderId()).get();

          Shipment shipment = new Shipment();
          shipment.setAwbCode(trackingData.getShipmentTrack().get(0).getAwbCode());
          shipment.setShipmentId(trackingData.getShipmentTrack().get(0).getShipmentId());
          shipment.setPickupDate(trackingData.getShipmentTrack().get(0).getPickupDate());
          shipment.setDeliveredDate(trackingData.getShipmentTrack().get(0).getDeliveredDate());
          shipment.setCurrentStatus(Integer.parseInt((trackingData.getShipmentTrack().get(0).getCurrentStatus())));
          shipment.setDeliveredTo(trackingData.getShipmentTrack().get(0).getDeliveredTo());
          shipment.setDestination(trackingData.getShipmentTrack().get(0).getDestination());
          shipment.setOrigin(trackingData.getShipmentTrack().get(0).getOrigin());
          shipment.setEdd(trackingData.getShipmentTrack().get(0).getEdd());
          System.out.println(shipment);
//          transaction.setShipment(shipment);
//          shipment.setTransaction(transaction);
//          // Save the transaction entity to the database using JPA
//          transactionRepository.save(transaction);
     }

     public List<TransactionDTO> getHistoryOfOrdersById(Long id) {
          List<Transaction> transactions = transactionRepository.findByCustomer_Id(id);
          List<TransactionDTO> historyOfOrders = transactions.stream()
                  .map(transactionMapper::mapToDTO)
                  .toList();

          return historyOfOrders;
     }
}
