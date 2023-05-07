package com.example.madrasdaapi.dto.commons;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CancelRequestDTO {
    private Long id;
    private TransactionDTO transaction;
    private String reason;
    private String images;
    private String phone;

}
