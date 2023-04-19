package com.example.madrasdaapi.models.ShiprocketModels;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NewOrder {

    @JsonProperty("pickup_location")
    private String pickupLocation;

    private String weight;

    @JsonProperty("billing_phone")
    private String billingPhone;

    @JsonProperty("billing_customer_name")
    private String billingCustomerName;

    @JsonProperty("billing_country")
    private String billingCountry;

    @JsonProperty("billing_address")
    private String billingAddress;

    @JsonProperty("billing_city")
    private String billingCity;

    @JsonProperty("billing_state")
    private String billingState;

    @JsonProperty("billing_email")
    private String billingEmail;

    @JsonProperty("billing_pincode")
    private String billingPincode;

    @JsonProperty("shipping_is_billing")
    private Boolean shippingIsBilling;

    @JsonProperty("shipping_phone")
    private String shippingPhone;

    @JsonProperty("shipping_customer_name")
    private String shippingCustomerName;

    @JsonProperty("shipping_country")
    private String shippingCountry;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_city")
    private String shippingCity;

    @JsonProperty("shipping_state")
    private String shippingState;

    @JsonProperty("shipping_email")
    private String shippingEmail;

    @JsonProperty("shipping_pincode")
    private String shippingPincode;

    @JsonProperty("order_date")
    private String orderDate;

    @JsonProperty("sub_total")
    private String subTotal;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("channel_id")
    private String channelId;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("order_items")
    private List<ShipRocketOrderItem> orderItems;
}
