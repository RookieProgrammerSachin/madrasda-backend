package com.example.madrasdaapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class DemoController {
    @GetMapping("test")
    public String hello(){
        return "its working";
    }
}
