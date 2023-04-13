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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
        transaction.setBillingUser(userRepository.findByEmail(SecurityContextHolder.getDeferredContext().get().getAuthentication().getName()).get());
        BigDecimal orderTotal = new BigDecimal(0L);
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO item: transactionDTO.getOrderItems()) {
            OrderItem orderItem = mapper.map(item, OrderItem.class);
            orderItem.setSku(item.getProduct().getColors().get(0).getSizes().get(0).getSku());
            orderItem.setTransaction(transaction);
            orderItem.setSize(mapper.map(item.getProduct().getColors().get(0).getSizes().get(0), Size.class));
            orderItem.setColor(mapper.map(item.getProduct().getColors().get(0).getColor(), Color.class));
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);
            orderTotal = orderTotal.add(orderItem.getProduct().getTotal().multiply(BigDecimal.valueOf(orderItem.getQuantity())));

        }
        transaction.setOrderItems(orderItems);
        transaction.setOrderTotal(orderTotal);

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
