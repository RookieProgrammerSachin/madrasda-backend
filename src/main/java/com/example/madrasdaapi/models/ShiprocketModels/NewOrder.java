package com.example.madrasdaapi.models.ShiprocketModels;

import lombok.Data;

import java.util.List;

@Data
public class NewOrder {
    private String pickupLocation;
    private String weight;
    private String billingPhone;
    private String billingCustomerName;
    private String billingCountry;
    private String billingAddress;
    private String billingCity;
    private String billingState;
    private String billingEmail;
    private String billingPincode;
    private Boolean shippingIsBilling;
	private String shippingPhone;
	private String shippingCustomerName;
	private String shippingCountry;
	private String shippingAddress;
	private String shippingCity;
	private String shippingState;
	private String shippingEmail;
	private String shippingPincode;
    private String orderDate;
    private String subTotal;
    private String orderId;
    private String channelId;
    private String paymentMethod;
    private List<ShipRocketOrderItem> orderItems;
}


