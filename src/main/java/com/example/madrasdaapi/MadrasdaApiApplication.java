package com.example.madrasdaapi;

import com.example.madrasdaapi.config.ShipRocketProperties;
import com.example.madrasdaapi.dto.commons.NewProductDTO;
import com.example.madrasdaapi.dto.commons.TransactionDTO;
import com.example.madrasdaapi.models.Product;
import com.example.madrasdaapi.models.Transaction;
import com.google.gson.Gson;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import okhttp3.OkHttpClient;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(NewProductDTO.class, Product.class)
                .addMappings(new PropertyMap<NewProductDTO, Product>() {
                    @Override
                    protected void configure() {
                        skip(destination.getProductImages());
                    }
                });
        mapper.createTypeMap(Transaction.class, TransactionDTO.class)
                .addMapping(Transaction::getOrderItems, TransactionDTO::setOrderItems);

        return mapper;
}

    @Bean
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(clientId, clientSecret);
    }

    @Bean
    Gson gson() {
        return new Gson();
    }
}
