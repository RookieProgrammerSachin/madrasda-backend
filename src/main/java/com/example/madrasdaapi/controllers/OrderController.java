package com.example.madrasdaapi.controllers;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order/")
@RequiredArgsConstructor
public class OrderController {
    private final OkHttpClient okHttpClient;
//    private final
//    @GetMapping("getAllOrders")

}
