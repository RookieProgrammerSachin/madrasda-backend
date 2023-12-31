package com.example.madrasdaapi.dto.ShiprocketModels;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class NewOrder {

    @JsonProperty("pickup_location")
    private String pickupLocation = "";

    private Float weight;

    @JsonProperty("billing_phone")
    private String billingPhone = "";

    @JsonProperty("billing_customer_name")
    private String billingCustomerName = "";

    @JsonProperty("billing_last_name")
    private String billingLastName = "";

    @JsonProperty("billing_country")
    private String billingCountry = "";

    @JsonProperty("billing_address")
    private String billingAddress = "";

    @JsonProperty("billing_city")
    private String billingCity = "";

    @JsonProperty("billing_state")
    private String billingState = "";

    @JsonProperty("billing_email")
    private String billingEmail = "";

    @JsonProperty("billing_pincode")
    private String billingPincode = "";

    @JsonProperty("shipping_is_billing")
    private Boolean shippingIsBilling;

    @JsonProperty("shipping_phone")
    private String shippingPhone = "";

    @JsonProperty("shipping_customer_name")
    private String shippingCustomerName = "";

    @JsonProperty("shipping_country")
    private String shippingCountry = "";

    @JsonProperty("shipping_address")
    private String shippingAddress = "";

    @JsonProperty("shipping_city")
    private String shippingCity = "";

    @JsonProperty("shipping_state")
    private String shippingState = "";

    @JsonProperty("shipping_email")
    private String shippingEmail = "";

    @JsonProperty("shipping_pincode")
    private String shippingPincode = "";

    @JsonProperty("order_date")
    private String orderDate = "";

    @JsonProperty("sub_total")
    private String subTotal = "";

    @JsonProperty("order_id")
    private String orderId = "";

    @JsonProperty("channel_id")
    private String channelId = "";

    @JsonProperty("payment_method")
    private String paymentMethod = "";

    private Float height;

    private Float breadth;

    private Float length;

    @JsonProperty("order_total")
    private BigDecimal orderTotal;

    private Double shipping_charges;

    @JsonProperty("order_items")
    private List<ShipRocketOrderItem> orderItems;
}
