package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.dto.ShipmentDTO;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.mappers.ShipmentMapper;
import com.example.madrasdaapi.mappers.TransactionMapper;
import com.example.madrasdaapi.models.Shipment;
import com.example.madrasdaapi.models.enums.ShipmentStatus;
import com.example.madrasdaapi.dto.TrackingDataDTO.TrackingData;
import com.example.madrasdaapi.models.Transaction;
import com.example.madrasdaapi.repositories.OrderRepository;
import com.example.madrasdaapi.repositories.ShipmentRepository;
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
     private final ShipmentMapper shipmentMapper;
     private final ShipmentRepository shipmentRepository;

     public TransactionDTO initiateTransaction(TransactionDTO transactionDTO) {
          Transaction transaction = transactionMapper.mapToEntity(transactionDTO);
          Shipment shipmentDetails = new Shipment();
          shipmentDetails.setCurrentStatus(ShipmentStatus.Order_Placed.name());
          transaction.setShipment(shipmentDetails);
          return transactionMapper.mapToDTO(transactionRepository.save(transaction));

     }

     public void updateStatus(TrackingData trackingData) {
          Transaction transaction = transactionRepository.findById(Long.parseLong(trackingData.getOrderId())).get();
          Shipment shipment = shipmentMapper.mapToShipment(trackingData);
          transaction.setShipment(shipment);
          shipment.setTransaction(transaction);
//          // Save the transaction entity to the database using JPA
          transactionRepository.save(transaction);
     }

     public List<TransactionDTO> getHistoryOfOrdersById(Long id) {
          List<Transaction> transactions = transactionRepository.findByCustomer_Id(id);
          List<TransactionDTO> historyOfOrders = transactions.stream()
                  .map(transactionMapper::mapToDTO)
                  .toList();

          return historyOfOrders;
     }

     public ShipmentDTO getOrderDetails(Long transactionId) {
          ShipmentDTO shipmentDetails = shipmentMapper.mapToDTO(shipmentRepository.findByTransaction_Id(transactionId));
          return shipmentDetails;
     }
}
