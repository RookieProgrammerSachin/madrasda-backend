package com.example.madrasdaapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthContext {
    public static String getCurrentUser() {
        return SecurityContextHolder.getDeferredContext().get().getAuthentication().getName();
    }

}
