package com.example.madrasdaapi.config;

import com.example.madrasdaapi.dto.ShipRocketDTO.ShipRocketLogin;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipRocketLoginResponse;
import com.example.madrasdaapi.security.JwtService;
import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.IOException;

@ConfigurationProperties(prefix = "shiprocket")
@RequiredArgsConstructor
public class ShipRocketProperties {
    private final JwtService jwtService;
    private final Gson gson;
    private final OkHttpClient okHttpClient;

    private static String token;

    public String getToken() throws IOException {
        if (jwtService.isTokenExpired(token)) {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(gson.toJson(new ShipRocketLogin("api@madrasda.com", "Braveheart@123")),
                    mediaType);
            Response response = okHttpClient.newCall(new Request.Builder()
                    .url("https://apiv2.shiprocket.in/v1/external/auth/login")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build()).execute();
            ResponseBody responseBody = response.body();
            setToken(gson.fromJson(responseBody.string(), ShipRocketLoginResponse.class).getToken());
            response.close();
        }
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
