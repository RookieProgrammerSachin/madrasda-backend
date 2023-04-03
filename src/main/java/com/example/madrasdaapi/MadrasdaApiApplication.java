package com.example.madrasdaapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MadrasdaApiApplication {

     public static void main(String[] args) {
          SpringApplication.run(MadrasdaApiApplication.class, args);
     }
     @Bean
     ModelMapper modelMapper(){
          return new ModelMapper();
     }

}
