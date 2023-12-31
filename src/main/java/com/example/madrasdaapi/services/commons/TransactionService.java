package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.config.AuthContext;
import com.example.madrasdaapi.config.ShipRocketProperties;
import com.example.madrasdaapi.dto.RazorPayDTO.PaymentLinkResult.PaymentLinkResult;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipmentDTO;
import com.example.madrasdaapi.dto.ShipRocketDTO.TrackingData;
import com.example.madrasdaapi.dto.ShiprocketModels.NewOrder;
import com.example.madrasdaapi.dto.ShiprocketModels.OrderDetails.ShiprocketOrderDetail;
import com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier.AvailableCourierCompaniesItem;
import com.example.madrasdaapi.dto.ShiprocketModels.RecommendedCourier.ServiceableCourierData;
import com.example.madrasdaapi.dto.ShiprocketModels.ShipRocketOrderItem;
import com.example.madrasdaapi.dto.commons.CancelRequestDTO;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.exception.APIException;
import com.example.madrasdaapi.exception.ResourceNotFoundException;
import com.example.madrasdaapi.mappers.ShipmentMapper;
import com.example.madrasdaapi.mappers.TransactionMapper;
import com.example.madrasdaapi.models.*;
import com.example.madrasdaapi.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.json.JSONObject;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ShipmentRepository shipmentRepository;
    private final VendorRepository vendorRepository;
    private final CartItemRepository cartItemRepository;
    private final CancelRequestRepository cancelRequestRepository;
    private final TransactionMapper transactionMapper;
    private final ShipmentMapper shipmentMapper;
    private final RazorpayClient razorpayClient;
    private final OkHttpClient okHttpClient;
    private final ShipRocketProperties shiprocket;


    public String initiateTransaction(TransactionDTO orderRequest) {
        //Total payable amount is calculated here
        Transaction transaction = transactionMapper.mapToEntity(orderRequest);
        transaction.getShippingAddress().setUser(transaction.getBillingUser());
        transaction.getShippingAddress().setName(orderRequest.getShippingAddress().getName());
        if (!orderRequest.getBillingIsShipping()) {
            transaction.getShippingAddress().setIsBillingUser(false);
        } else {
            transaction.setBillingIsShipping(true);
            transaction.getShippingAddress().setIsBillingUser(true);
        }
        String shortLink = null;
        //Create payment option
        try {
            PaymentLink link = createRazorPayLink(transaction,
                    orderRequest.getShippingAddress().getPostalCode());
            transaction.setPaymentId(link.get("id"));
            transactionRepository.save(transaction);
            shortLink = link.get("short_url");
            return link.get("short_url");
        } catch (RazorpayException | IOException exception) {
            exception.printStackTrace();
        }
        return shortLink;
    }

    @Transactional
    public void updateTransactionStatus(PaymentLinkResult result) throws IOException {
//        JSONObject payload = new JSONObject(result.getPayload());
//        Utils.verifyPaymentLink(payload, "BraveHeart");
        String paymentId = result.getPayload().getPaymentLink().getEntity().getId();
        Transaction transaction = transactionRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "paymentId", paymentId));

        if (transaction.getPaymentStatus() != null && transaction.getPaymentStatus().equals("payment_link.paid"))
            throw new APIException("Payment already accepted", HttpStatus.CONFLICT);
        String orderId = String.valueOf(System.currentTimeMillis());
        transaction.setOrderId(orderId.substring(orderId.length() - 10));
        if (result.getEvent().equals("payment_link.paid")) {
            HashMap<Long, Vendor> vendors = new HashMap<>();
            //Calculate Vendor profit
            for (OrderItem item : transaction.getOrderItems()) {
                Vendor vendor = item.getProduct().getVendor();
                vendor.setOutstandingProfit(vendor.getOutstandingProfit()
                        .add(item.getProduct().getProfit().multiply(BigDecimal.valueOf(item.getQuantity()))));
                vendors.put(item.getProduct().getId(), vendor);
            }
            BigDecimal individualShippingCharge = transaction.getShippingCharge().divide(BigDecimal.valueOf(vendors.size()))
                    .setScale(2, RoundingMode.CEILING);
            if(transaction.getOrderTotal().compareTo(BigDecimal.valueOf(500)) > 0)
                vendors.forEach((id, vendor) -> vendor.
                        setOutstandingProfit(vendor.getOutstandingProfit().subtract(individualShippingCharge)));

            transaction.setPaymentStatus(result.getEvent());
            NewOrder order = createShiprocketOrder(transaction);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(new ObjectMapper().writeValueAsString(order), mediaType);
            Request request = new Request.Builder().url("https://apiv2.shiprocket.in/v1/external/orders/create/adhoc")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + shiprocket.getToken()).build();

            Response response = okHttpClient.newCall(request).execute();
            ShiprocketOrderDetail data = new ObjectMapper().readValue(response.body().bytes(), ShiprocketOrderDetail.class);
            TrackingData trackingData = new TrackingData();
            trackingData.setAwb(trackingData.getAwb());
            trackingData.setCurrentStatus(data.getStatus());
            trackingData.setCurrentStatusId(data.getStatusCode());
            trackingData.setCourierName(data.getCourierName());
            trackingData.setOrderId(data.getOrderId());
            transaction.setOrderId(data.getOrderId());
            response.close();
            Shipment shipment = shipmentMapper.mapToShipment(trackingData);
            shipment.setTransaction(transaction);
            transaction.setShipment(shipment);
            shipmentRepository.save(shipment);
            cartItemRepository.deleteByCustomer_Id(transaction.getBillingUser().getId());
            vendorRepository.saveAll(vendors.values());
            transactionRepository.save(transaction);
        }
        else{
            transactionRepository.save(transaction);
            transaction.setPaymentStatus(result.getEvent());
        }
    }

    public void updateShipmentStatus(TrackingData trackingData) {
        Transaction transaction = transactionRepository.findByOrderId(trackingData.getOrderId())
                .orElseThrow(() -> new APIException("Order Not found", HttpStatus.OK));
        Shipment shipment = shipmentMapper.mapToShipment(trackingData);
        shipment.setId(transaction.getShipment().getId());
        transaction.setShipment(shipment);
        shipment.setTransaction(transaction);
        transactionRepository.save(transaction);
    }

    private PaymentLink createRazorPayLink(Transaction transaction, String pincode) throws RazorpayException, IOException {
        JSONObject options = new JSONObject();
        BigDecimal shippingCharges = BigDecimal.valueOf(calculateShippingCharges(pincode, false));
        transaction.setShippingCharge(shippingCharges);
        BigDecimal customerShippingCharge = shippingCharges;
        if(transaction.getOrderTotal().compareTo(BigDecimal.valueOf(500)) >= 0)
            customerShippingCharge = BigDecimal.ZERO;

        options.put("amount", transaction.getOrderTotal() //with deduction
//                .multiply(BigDecimal.valueOf(((double) 105) / 100)) removed tax
                .add(customerShippingCharge)
                .setScale(0, RoundingMode.CEILING)
                .multiply(new BigDecimal(100))
                .longValueExact());
        options.put("currency", "INR");
        JSONObject customer = new JSONObject();
        customer.put("name", transaction.getBillingUser().getName());
        customer.put("contact", transaction.getBillingUser().getPhone());
        customer.put("email", transaction.getBillingUser().getEmail());
        options.put("customer", customer);
        options.put("callback_url", "https://madrasda.com/clientprofile");
        return razorpayClient.paymentLink.create(options);
    }

    private NewOrder createShiprocketOrder(Transaction transaction)  {
        NewOrder order = new NewOrder();
        order.setOrderId(transaction.getOrderId());
        order.setOrderDate(transaction.getOrderDate().toString());
        order.setPickupLocation("MADRAS DA");
        List<ShipRocketOrderItem> orderItems = new ArrayList<>();
        Float height = 0.0F;
        Float length = 0.0F;
        Float breadth = 0.0F;
        Float weight = 0.0F;
        Double shippingCharges = transaction.getShippingCharge().doubleValue();
        for (OrderItem item : transaction.getOrderItems()) {
            ShipRocketOrderItem orderItem = new ShipRocketOrderItem();
            Product product = item.getProduct();
            orderItem.setName(product.getName());
            orderItem.setTax(BigDecimal.ZERO);
            orderItem.setDiscount(product.getTotal()
                    .multiply(product.getDiscount().divide(BigDecimal.valueOf(100), RoundingMode.CEILING)));
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
        Customer billingAddress = transaction.getShippingAddress();
        order.setBillingAddress(billingAddress.getAddressLine1() + " " + billingAddress.getAddressLine2());
        order.setBillingCustomerName(billingAddress.getName());
        order.setBillingCity(billingAddress.getCity());
        order.setBillingCountry(billingAddress.getCountry());
        order.setBillingState(billingAddress.getState());
        order.setBillingEmail(billingAddress.getEmail());
        order.setBillingPhone(transaction.getBillingUser().getPhone());
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
        order.setWeight(weight);
        order.setHeight(height);
        order.setBreadth(breadth);
        order.setLength(length);
        order.setShipping_charges(shippingCharges);
        order.setShippingIsBilling(transaction.getBillingIsShipping());
        order.setPaymentMethod("PREPAID");;
        order.setSubTotal(transaction.getOrderTotal().toString()); //with deduction
        order.setOrderItems(orderItems);
        return order;
    }

    public List<TransactionDTO> getHistoryOfOrdersByCustomerId(String phone) {
        List<Transaction> transactions = transactionRepository.findByBillingUser_PhoneAndPaymentStatusLike(phone, "payment_link.paid");

        return transactions.stream().map(transactionMapper::mapToDTO).toList();
    }

    public Page<TransactionDTO> getAllOrders(int pageNo, int pageSize) {
        Page<Transaction> transactions = transactionRepository.findAllByShipment_CurrentStatusOrderById("NEW", PageRequest.of(pageNo, pageSize));
        return transactions.map(transactionMapper::mapToDTO);
    }

    public ShipmentDTO getOrderDetails(Long transactionId) {
        return shipmentMapper.mapToDTO(shipmentRepository.findByTransaction_Id(transactionId));
    }

    private Double requestFreightCharges(String pincode, Float height, Float length, Float breadth, Float weight) throws IOException {
        Request request = new Request.Builder().url("https://apiv2.shiprocket.in/v1/external/courier/serviceability?pickup_postcode=600087" +
                        "&height=" + height + "&weight=" + weight + "&breadth=" + breadth + "&length=" + length +
                        "&delivery_postcode=" + pincode + "&cod=0")
                .method("GET", null).addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + shiprocket.getToken()).build();

        Response response = okHttpClient.newCall(request).execute();
        String json = response.body().string();
        response.close();
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        int code = data.get("status").getAsInt();
        if (code != 200) throw new APIException(data.get("message").getAsString(), HttpStatus.OK);
        Integer courierId = data.getAsJsonObject("data").get("recommended_courier_company_id").getAsInt();
        Type listType = new TypeToken<List<AvailableCourierCompaniesItem>>(){}.getType();
        JsonArray companies = data.getAsJsonObject("data").getAsJsonArray("available_courier_companies");

        for (JsonElement company : companies) {
            if (courierId.equals(company.getAsJsonObject().get("courier_company_id").getAsInt())) {
                return Math.ceil(company.getAsJsonObject().get("freight_charge").getAsDouble());
            }
        }

        throw new APIException("No eligible couriers found", HttpStatus.BAD_REQUEST);
    }

    public Double calculateShippingCharges(String pincode, boolean isCustomer) throws IOException {
        String phone = AuthContext.getCurrentUser();
        Float height = 0.0F;
        Float length = 0.0F;
        Float breadth = 0.0F;
        Float weight = 0.0F;
        Float totalCost = 0.0f;
        List<CartItem> cart = cartItemRepository.findByCustomer_Phone(phone);
        if (cart.size() == 0) throw new APIException("Cart is Empty", HttpStatus.CONFLICT);
        for (CartItem item : cart) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            BigDecimal total = product.getTotal();
            BigDecimal discount = product.getDiscount();
            BigDecimal discountedTotal = total.subtract(total.multiply(discount).divide(BigDecimal.valueOf(100)));
            height += product.getHeight() * quantity;
            weight += product.getWeight() * quantity;
            breadth = Math.max(product.getBreadth(), breadth);
            length = Math.max(product.getLength(), length);
            totalCost += quantity * discountedTotal.floatValue();
        }
        totalCost = (float) Math.round(totalCost); //499.5 will be rounded off to 500
        if (isCustomer && totalCost >= 500) return 0.00d;
        return requestFreightCharges(pincode, height, length, breadth, weight);
    }

    public void cancelOrder(CancelRequestDTO cancelRequestDTO) {
        Transaction transaction = transactionRepository.findById(cancelRequestDTO.getTransaction().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", cancelRequestDTO.getTransaction().getId().toString()));
        long diffInMillis = new Date().getTime() - transaction.getOrderDate().getTime();
        if (diffInMillis > 5 * 60 * 1000L) {
            throw new APIException("5 Minutes Time Limit Exceeded by " + diffInMillis + "ms", HttpStatus.CONFLICT);
        }
        CancelRequest request = new CancelRequest();
        request.setTransaction(transaction);
        request.setReason(cancelRequestDTO.getReason());
        request.setImages(cancelRequestDTO.getImages());
        cancelRequestRepository.save(request);
        transaction.setCancelRequested(true);
        transactionRepository.save(transaction);
    }

    @Transactional
    public void resolveCancelOrder(Long transactionId) throws IOException {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionId.toString()));
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"ids\": [" + transaction.getOrderId() + "]}");
//        System.out.println("{\"ids\": [" + transaction.getOrderId() + "]}");
        Request request = new Request.Builder()
                .url("https://apiv2.shiprocket.in/v1/external/orders/cancel")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + shiprocket.getToken())
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            System.out.println(response.body().string());
            throw new APIException("Error cancelling order", HttpStatus.valueOf(response.code()));
        }
        //Deduct from Vendor's Total Profit
        HashMap<Vendor, BigDecimal> deductibleProfit = new HashMap<>();
        transaction.getOrderItems()
                .forEach(item -> {
                    Product product = item.getProduct();
                    Vendor vendor = product.getVendor();
                    deductibleProfit.put(vendor,
                            deductibleProfit.getOrDefault(vendor, BigDecimal.ZERO).add(product.getProfit()));

                });
        int vendorCount = deductibleProfit.size();
        BigDecimal shippingCharge = transaction.getShippingCharge().divide(BigDecimal.valueOf(vendorCount),
                RoundingMode.CEILING);
        deductibleProfit.forEach((vendor, profit) -> {
            BigDecimal outstandingProfit = vendor.getOutstandingProfit();
            BigDecimal newOutstandingProfit = outstandingProfit.subtract(profit.subtract(shippingCharge));
            vendor.setOutstandingProfit(newOutstandingProfit.max(BigDecimal.ZERO));
            vendorRepository.save(vendor);
        });
        transaction.setCancelled(true);
        transaction.setCancelRequested(false);
        transactionRepository.save(transaction);
        cancelRequestRepository.deleteByTransaction_Id(transaction.getId());
    }

    public Page<CancelRequestDTO> getAllCancelRequests(int pageNo, int pageSize) {
        return cancelRequestRepository.findAll(PageRequest.of(pageNo, pageSize))
                .map(transactionMapper::mapToCancelRequestDTO);
    }


}
