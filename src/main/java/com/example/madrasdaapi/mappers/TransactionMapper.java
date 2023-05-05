package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipmentTrackActivityDTO;
import com.example.madrasdaapi.dto.commons.OrderItemDTO;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.models.*;
import com.example.madrasdaapi.models.enums.ShipmentStatus;
import com.example.madrasdaapi.repositories.CartItemRepository;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.ProductSKUMappingRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
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
    private final ProductRepository productRepository;
    private final ProductSKUMappingRepository productSKUMappingRepository;
    private final CartItemRepository cartItemRepository;


    public Transaction mapToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.map(transactionDTO, transaction);
        User user = userRepository.findByPhone(AuthContext.getCurrentUser()).get();
        transaction.setBillingUser(user);
        Customer shippingAddress = mapper.map(transactionDTO.getShippingAddress(), Customer.class);
        transaction.setShippingAddress(shippingAddress);
        BigDecimal orderTotal = new BigDecimal(0L);
        List<OrderItem> orderItems = new ArrayList<>();
        List<CartItem> cart = user.getCart();
        for (CartItem item : cart) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSku(item.getSku().getSku());
            orderItem.setTransaction(transaction);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProduct(productRepository.findById(item.getProduct().getId()).get());
            ProductSKUMapping sku = productSKUMappingRepository.findBySku(orderItem.getSku());
            orderItem.setSize(sku.getSize());
            orderItem.setColor(sku.getColor());
            orderItems.add(orderItem);
            orderTotal = orderTotal.add(orderItem.getProduct()
                    .getTotal().multiply(BigDecimal.valueOf(orderItem.getQuantity())).multiply(BigDecimal.valueOf((100 - orderItem.getProduct().getDiscount().doubleValue()) / 100)));

        }
        transaction.setOrderItems(orderItems);
        transaction.setOrderTotal(orderTotal);

        return transaction;
    }

    public TransactionDTO mapToDTO(Transaction transaction) {

        TransactionDTO transactionDTO = mapper.map(transaction, TransactionDTO.class);

        if (transaction.getShipment() != null) {
            transactionDTO.setShipmentActivity(transaction.getShipment()
                    .getScans()
                    .stream()
                    .map(item -> mapper.map(item, ShipmentTrackActivityDTO.class))
                    .collect(Collectors.toList()));
            transactionDTO.setStatus(ShipmentStatus.getNameByCode(transaction.getShipment().getCurrentStatusId()));
        }
        return transactionDTO;
    }

}
