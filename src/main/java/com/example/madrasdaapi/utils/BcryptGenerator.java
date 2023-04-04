package com.example.madrasdaapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGenerator {
     public static void main(String[] args) {
          System.out.println(new BCryptPasswordEncoder().encode("password"));
     }
}
