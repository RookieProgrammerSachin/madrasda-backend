package com.example.madrasdaapi.models.ShiprocketModels;

import lombok.Data;

@Data
public class OrderItem {
	private String sellingPrice;
	private String hsn;
	private String imageUrl;
	private String name;
	private String discount;
	private ImageDimensions imageDimensions;
	private String tax;
	private String units;
	private String sku;
	private String designUrl;
}