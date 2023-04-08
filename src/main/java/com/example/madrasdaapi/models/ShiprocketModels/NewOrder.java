package com.example.madrasdaapi.models.ShiprocketModels;

import java.util.List;
import lombok.Data;

@Data
public class NewOrder{
	private String billingPhone;
	private String pickupLocation;
	private String billingCountry;
	private String weight;
	private String billingCustomerName;
	private String billingAddress;
	private String orderDate;
	private String billingCity;
	private Boolean shippingIsBilling;
	private String billingState;
	private String billingEmail;
	private String billingPincode;
	private String subTotal;
	private String orderId;
	private String channelId;
	private String paymentMethod;
	private List<OrderItem> orderItems;
}


