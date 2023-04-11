package com.example.madrasdaapi.controllers;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order/")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OkHttpClient okHttpClient;
//    private final
//    @GetMapping("getAllOrders")

}
