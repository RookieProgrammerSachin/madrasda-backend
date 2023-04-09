package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.commons.OrderItemDTO;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.OrderItem;
import com.example.madrasdaapi.models.Size;
import com.example.madrasdaapi.models.Transaction;
import com.example.madrasdaapi.models.enums.ShipmentStatus;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
    private final ModelMapper mapper;
    private final UserRepository userRepository;


    public Transaction mapToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.map(transactionDTO, transaction);
        transaction.setBillingUser(userRepository.findById(transactionDTO.getBillingCustomer().getId()).get());

        transaction.setOrderItems(transactionDTO.getOrderItems()
                .stream()
                .map(item -> {
                    OrderItem orderItem = mapper.map(item, OrderItem.class);
                    orderItem.setSku(item.getProduct().getColors().get(0).getSizes().get(0).getSku());
                    orderItem.setTransaction(transaction);
                    orderItem.setSize(mapper.map(item.getProduct().getColors().get(0).getSizes().get(0), Size.class));
                    orderItem.setColor(mapper.map(item.getProduct().getColors().get(0).getColor(), Color.class));
                    orderItem.setQuantity(item.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toSet()));
        return transaction;
    }

    public TransactionDTO mapToDTO(Transaction transaction) {
        mapper.typeMap(OrderItem.class, OrderItemDTO.class);
        TransactionDTO transactionDTO = mapper.createTypeMap(Transaction.class, TransactionDTO.class)
                .addMapping(Transaction::getOrderItems, TransactionDTO::setOrderItems)
                .map(transaction);
        transactionDTO.setShipmentActivity(transaction.getShipment().getScans());
        transactionDTO.setStatus(ShipmentStatus.getNameByCode(transaction.getShipment().getCurrentStatusId()));
        return transactionDTO;
    }
}
