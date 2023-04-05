package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.commons.OrderItemDTO;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.models.OrderItem;
import com.example.madrasdaapi.models.ShipmentStatus;
import com.example.madrasdaapi.models.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
     private final ModelMapper mapper;


     public Transaction mapToEntity(TransactionDTO transactionDTO) {
          Transaction transaction = new Transaction();
          mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
          mapper.map(transactionDTO, transaction);
          transaction.setOrderItems(transactionDTO.getOrderItems()
                  .stream()
                  .map(item -> mapper.map(item, OrderItem.class))
                  .collect(Collectors.toSet()));
          return transaction;
     }
     public TransactionDTO mapToDTO(Transaction transaction){
          mapper.typeMap(OrderItem.class, OrderItemDTO.class);
          TransactionDTO transactionDTO = mapper.createTypeMap(Transaction.class, TransactionDTO.class)
                  .addMapping(Transaction::getOrderItems, TransactionDTO::setOrderItems)
                  .map(transaction);
          transactionDTO.setShipmentActivity(transaction.getShipment().getShipmentActivities());
          transactionDTO.setStatus(ShipmentStatus.getNameByCode(transaction.getShipment().getCurrentStatus()));
          return transactionDTO;
     }
}
