package com.example.madrasdaapi.dto.ShipRocketDTO;

import lombok.Data;

@Data
public class ShipRocketLoginResponse{
	private Integer id;
	private Integer companyId;
	private String lastName;
	private String createdAt;
	private String firstName;
	private String email;
	private String token;
}