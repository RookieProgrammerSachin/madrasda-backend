package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.config.ShipRocketProperties;
import com.example.madrasdaapi.dto.RazorPayDTO.PaymentRequest.OrderResponse;
import com.example.madrasdaapi.dto.RazorPayDTO.PaymentRequest.PaymentRequest;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipmentDTO;
import com.example.madrasdaapi.dto.ShipRocketDTO.TrackingData;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.mappers.CustomerMapper;
import com.example.madrasdaapi.mappers.ShipmentMapper;
import com.example.madrasdaapi.mappers.TransactionMapper;
import com.example.madrasdaapi.models.*;
import com.example.madrasdaapi.models.ShiprocketModels.NewOrder;
import com.example.madrasdaapi.models.ShiprocketModels.RecommendedCourier.AvailableCourierCompany;
import com.example.madrasdaapi.models.ShiprocketModels.RecommendedCourier.ServiceableCourierData;
import com.example.madrasdaapi.models.ShiprocketModels.ShipRocketOrderItem;
import com.example.madrasdaapi.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShipmentRepository shipmentRepository;
    private final ProductRepository productRepository;
    private final TransactionMapper transactionMapper;
    private final ShipmentMapper shipmentMapper;
    private final CustomerMapper customerMapper;
    private final RazorpayClient razorpayClient;
    private final Gson gson;
    private final OkHttpClient okHttpClient;
    private final ShipRocketProperties shiprocket;
    private final VendorRepository vendorRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    @Value("${razorpay.keySecret}")
    private String SECRET_KEY;
    @Value("${razorpay.keyId}")
    private String SECRET_ID;

    public String initiateTransaction(TransactionDTO orderRequest) {
        //Total payable amount is calculated here
        Transaction transaction = transactionMapper.mapToEntity(orderRequest);
        transaction.getShippingAddress().setUser(transaction.getBillingUser());
        transaction.getShippingAddress().setName(orderRequest.getShippingAddress().getName());
        if (!orderRequest.getBillingIsShipping()) {
            transaction.getShippingAddress().setIsBillingUser(false);
        } else {
            transaction.setBillingIsShipping(true);
            Customer billingAddress = customerRepository.findByIdAndIsBillingUser(transaction.getBillingUser().getId(), true);
            if (billingAddress != null) transaction.setShippingAddress(billingAddress);
            transaction.getShippingAddress().setIsBillingUser(true);
        }
        OrderResponse response = new OrderResponse();
        String shortLink = null;
        //Create payment option
        try {
            transaction.setOrderTotal(transaction.getOrderTotal()
                    .add(BigDecimal.valueOf(calculateShippingCharges(orderRequest.getShippingAddress().getPostalCode()))));
            Order order = createRazorPayOrder(transaction.getOrderTotal());
            PaymentLink link = createRazorPayLink(transaction.getOrderTotal());
            transaction.setOrderId(order.get("id"));
            transactionRepository.save(transaction);
            shortLink = link.get("short_url");
            return link.get("short_url");
        } catch (RazorpayException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shortLink;
    }
    
    private Order createRazorPayOrder(BigDecimal amount) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount.multiply(new BigDecimal(100)));
        options.put("currency", "INR");
        Order order = razorpayClient.orders.create(options);
        return order;
    }
    private PaymentLink createRazorPayLink(BigDecimal amount) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount.multiply(new BigDecimal(100)));
        options.put("currency", "INR");
        PaymentLink paymentLink = razorpayClient.paymentLink.create(options);
        return paymentLink;
    }


    public void updateTransactionStatus(PaymentRequest result) throws RazorpayException, IOException {
        String orderId = result.getPayload().getPayment().getEntity().getOrderId();
        Transaction transaction = transactionRepository.findByOrderId(orderId).orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", orderId));
        if (transaction.getPaymentId() != null && transaction.getPaymentStatus().equals("payment.captured"))
            throw new APIException("Payment already accepted", HttpStatus.CONFLICT);
        transaction.setPaymentId(result.getPayload().getPayment().getEntity().getId());

        if (result.getEvent().equals("payment.captured")) {
            HashMap<Long, Vendor> vendors = new HashMap<>();
            //Calculate Vendor profit
            for (OrderItem item : transaction.getOrderItems()) {
                Vendor vendor = item.getProduct().getVendor();
                vendor.setOutstandingProfit(vendor.getOutstandingProfit()
                        .add(item.getProduct()
                                .getProfit()
                                .multiply(BigDecimal.valueOf(item.getQuantity()))));
                vendors.put(item.getProduct().getId(), vendor);
            }
            transaction.setPaymentStatus(result.getEvent());
            NewOrder order = createShiprocketOrder(transaction);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(new ObjectMapper().writeValueAsString(order), mediaType);
            Request request = new Request.Builder().url("https://apiv2.shiprocket.in/v1/external/orders/create/adhoc")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + shiprocket.getToken()).build();

            Response response = okHttpClient.newCall(request).execute();
            response.close();
            cartItemRepository.deleteByCustomer_Id(transaction.getBillingUser().getId());
            vendorRepository.saveAll(vendors.values());
            transactionRepository.save(transaction);
        }
        if (result.getEvent().equals("payment.failed")) {
            transactionRepository.save(transaction);
            transaction.setPaymentStatus(result.getEvent());
        }


    }

    private NewOrder createShiprocketOrder(Transaction transaction) throws RazorpayException {
        NewOrder order = new NewOrder();
        order.setOrderId(transaction.getOrderId());
        order.setOrderDate(transaction.getOrderDate().toString());
        order.setPickupLocation("MADRAS DA");
        List<ShipRocketOrderItem> orderItems = new ArrayList<>();
        Float height = 0.0F;
        Float length = 0.0F;
        Float breadth = 0.0F;
        Float weight = 0.0F;
        for (OrderItem item : transaction.getOrderItems()) {
            ShipRocketOrderItem orderItem = new ShipRocketOrderItem();
            Product product = item.getProduct();
            orderItem.setName(product.getName());
            orderItem.setDiscount(product.getDiscount().toString());
            orderItem.setTax(product.getTax().toString());
            orderItem.setHsn(product.getHsn());
            orderItem.setUnits(item.getQuantity());
            orderItem.setSku(item.getSku() + "-" + product.getVendor().getId() + "-" + product.getId());
            orderItem.setSellingPrice(product.getTotal().toString());
            height += product.getHeight() * item.getQuantity();
            weight += product.getWeight() * item.getQuantity();
            breadth = Math.max(product.getBreadth(), breadth);
            length = Math.max(product.getLength(), length);
            orderItems.add(orderItem);
        }
        order.setWeight(weight);
        order.setHeight(height);
        order.setBreadth(breadth);
        order.setLength(length);
        order.setOrderTotal(transaction.getOrderTotal());
        order.setOrderItems(orderItems);

        Customer billingAddress = customerRepository.findByIdAndIsBillingUser(transaction.getBillingUser().getId(), true);
        order.setBillingAddress(billingAddress.getAddressLine1() + " " + billingAddress.getAddressLine2());
        order.setBillingCustomerName(billingAddress.getName());
        order.setBillingCity(billingAddress.getCity());
        order.setBillingCountry(billingAddress.getCountry());
        order.setBillingState(billingAddress.getState());
        order.setBillingEmail(billingAddress.getEmail());
        order.setBillingPhone(billingAddress.getPhone());
        order.setBillingPincode(billingAddress.getPostalCode());
        if (!transaction.getBillingIsShipping()) {
            Customer shippingAddress = transaction.getShippingAddress();
            order.setShippingAddress(shippingAddress.getAddressLine1() + shippingAddress.getAddressLine2());
            order.setShippingCustomerName(shippingAddress.getName());
            order.setShippingCity(shippingAddress.getCity());
            order.setShippingCountry(shippingAddress.getCountry());
            order.setShippingEmail(shippingAddress.getEmail());
            order.setShippingPhone(shippingAddress.getPhone());
            order.setShippingPincode(shippingAddress.getPostalCode());
        }
        order.setShippingIsBilling(transaction.getBillingIsShipping());
        order.setPaymentMethod("PREPAID");
        order.setSubTotal(transaction.getOrderTotal().toString());
        order.setOrderItems(orderItems);
        return order;
    }

    public void updateShipmentStatus(TrackingData trackingData) {
        Transaction transaction = transactionRepository.findByOrderId(trackingData.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", trackingData.getOrderId()));
        Shipment shipment = shipmentMapper.mapToShipment(trackingData);
        transaction.setShipment(shipment);
        shipment.setTransaction(transaction);
        transactionRepository.save(transaction);
    }

    public List<TransactionDTO> getHistoryOfOrdersByCustomerId(String phone) {
        List<Transaction> transactions = transactionRepository.findByBillingUser_Phone(phone);

        return transactions.stream().map(transactionMapper::mapToDTO).toList();
    }

    public ShipmentDTO getOrderDetails(Long transactionId) {
        return shipmentMapper.mapToDTO(shipmentRepository.findByTransaction_Id(transactionId));
    }


    public Double calculateShippingCharges(String pincode) throws IOException {
        String phone = AuthContext.getCurrentUser();
        Float height = 0.0F;
        Float length = 0.0F;
        Float breadth = 0.0F;
        Float weight = 0.0F;
        List<CartItem> cart = cartItemRepository.findByCustomer_Phone(phone);
        for (CartItem item : cart) {
            height += item.getProduct().getHeight() * item.getQuantity();
            weight += item.getProduct().getWeight() * item.getQuantity();
            breadth = Math.max(item.getProduct().getBreadth(), breadth);
            length = Math.max(item.getProduct().getLength(), length);
        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://apiv2.shiprocket.in/v1/external/courier/serviceability?pickup_postcode=600087" +
                        "&height=" + height +
                        "&weight=" + weight +
                        "&breadth=" + breadth +
                        "&length=" + length +
                        "&delivery_postcode=" + pincode +
                        "&cod=0"
                )
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + shiprocket.getToken())
                .build();

        Response response = okHttpClient.newCall(request).execute();
        ServiceableCourierData serviceabilityResponse = new ObjectMapper()
                .readValue(response.body().bytes(), ServiceableCourierData.class);
        Integer courierId = serviceabilityResponse.getData().getRecommendedCourierCompanyId();
        List<AvailableCourierCompany> companies = serviceabilityResponse.getData().getAvailableCourierCompanies();
        AvailableCourierCompany recommendedCompany;
        for (AvailableCourierCompany company : companies) {
            if (company.getCourierCompanyId().equals(courierId)) {
                return company.getFreightCharge();
            }
        }
        response.close();
        throw new APIException("No eligible couriers found", HttpStatus.BAD_REQUEST);
    }

}
