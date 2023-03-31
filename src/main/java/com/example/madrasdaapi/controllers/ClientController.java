package com.example.madrasdaapi.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Customer Resource Controller")
@RequestMapping("/api/client/")

public class ClientController {
    @GetMapping("test")
    public String client(){
        return "Hello from client";
    }
}
