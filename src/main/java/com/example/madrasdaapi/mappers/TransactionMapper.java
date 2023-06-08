package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipmentTrackActivityDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupDTO;
import com.example.madrasdaapi.dto.VendorDTO.MockupImageDTO;
import com.example.madrasdaapi.dto.commons.*;
import com.example.madrasdaapi.models.*;
import com.example.madrasdaapi.models.enums.ShipmentStatus;
import com.example.madrasdaapi.repositories.CartItemRepository;
import com.example.madrasdaapi.repositories.ProductRepository;
import com.example.madrasdaapi.repositories.ProductSKUMappingRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
    private final ModelMapper mapper;
    private final MockupMapper mockupMapper;
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
            orderTotal = orderTotal.add(orderItem.getProduct().getTotal().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                    .multiply(BigDecimal.valueOf((100 - orderItem.getProduct().getDiscount().doubleValue()) / 100)));

        }
        transaction.setOrderItems(orderItems);
        transaction.setOrderTotal(orderTotal.setScale(0, RoundingMode.CEILING));

        return transaction;
    }
    public MockupDTO mapToProductMockupDTO(Mockup mockup){
        MockupDTO mockupDTO = new MockupDTO();
        BeanUtils.copyProperties(mockup, mockupDTO);
        return mockupDTO;
    }

    public TransactionDTO mapToDTO(Transaction transaction) {
        TransactionDTO transactionDTO = mapper.map(transaction, TransactionDTO.class);
        List<OrderItemDTO> items = new ArrayList<>();
        HashMap<Long, ColorDTO> imageDTOHashMap = new HashMap<>();
        HashMap<Long, Mockup> productMockups = new HashMap<>();
        for (OrderItem item : transaction.getOrderItems()) {
            String sku = item.getSku();
            ProductSKUMapping productSKUMapping = item.getProduct()
                    .getSkuMappings()
                    .stream()
                    .filter((productSku) -> productSku.getSku().equals(sku))
                    .findFirst().get();
            Long colorId = productSKUMapping.getColor().getId();
            String productImageDTO = "";
            String colorName = "";
            SizeDTO size = mapper.map(productSKUMapping.getSize(), SizeDTO.class);
            for (ProductImage image : item.getProduct().getProductImages()) {
                if (image.getColor().getId().equals(colorId)) {
                    productImageDTO = image.getImgUrl();
                    colorName = image.getColor().getColor();
                    break;
                }
            }
            ColorDTO colorDTO = new ColorDTO();
            colorDTO.setImages(List.of(productImageDTO));
            colorDTO.setColor(colorName);
            colorDTO.setSizes(List.of(size));
            imageDTOHashMap.put(item.getId(), colorDTO);
            productMockups.put(item.getProduct().getId(), item.getProduct().getMockup());
        }
        for (OrderItemDTO item : transactionDTO.getOrderItems()) {
            item.getProduct().setColors(List.of(imageDTOHashMap.get(item.getId())));
            item.getProduct().setProductMockup(
                    mapToProductMockupDTO(
                            productMockups.get((item.getProduct().getId()))
                    )
            );
        }
        if (transaction.getShipment() != null) {
            transactionDTO.setShipmentActivity(transaction.getShipment().getScans().stream().map(item -> mapper.map(item, ShipmentTrackActivityDTO.class)).collect(Collectors.toList()));
            transactionDTO.setStatus(ShipmentStatus.getNameByCode(transaction.getShipment().getCurrentStatusId()));
        }

        return transactionDTO;
    }

    public CancelRequestDTO mapToCancelRequestDTO(CancelRequest cancelRequest) {
        return mapper.map(cancelRequest, CancelRequestDTO.class);
    }

}
