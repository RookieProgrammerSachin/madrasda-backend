package com.example.madrasdaapi;

import com.example.madrasdaapi.config.ShipRocketProperties;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipRocketLoginResponse;
import com.google.gson.Gson;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import okhttp3.OkHttpClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
@EnableConfigurationProperties(ShipRocketProperties.class)
@SpringBootApplication
public class MadrasdaApiApplication {
    @Value("${razorpay.keyId}")
    private String clientId;

    @Value("${razorpay.keySecret}")
    private String clientSecret;

    public static void main(String[] args) {
        SpringApplication.run(MadrasdaApiApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    RazorpayClient razorpayClient() throws RazorpayException {  return new RazorpayClient(clientId, clientSecret); }

    @Bean
    Gson gson() { return new Gson(); }
}
