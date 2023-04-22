package com.example.madrasdaapi.config;

import com.example.madrasdaapi.dto.ShipRocketDTO.ShipRocketLogin;
import com.example.madrasdaapi.dto.ShipRocketDTO.ShipRocketLoginResponse;
import com.example.madrasdaapi.security.JwtService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@ConfigurationProperties(prefix = "shiprocket")

@RequiredArgsConstructor
public class ShipRocketProperties {
    private static String token;

    public String getToken() throws IOException {

        if (token == null || isTokenExpired(token)) {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(new Gson().toJson(new ShipRocketLogin("api@madrasda.com", "Braveheart@123")),
                    mediaType);
            Response response = new OkHttpClient().newCall(new Request.Builder()
                    .url("https://apiv2.shiprocket.in/v1/external/auth/login")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build()).execute();
            ResponseBody responseBody = response.body();
            setToken(new Gson().fromJson(responseBody.string(), ShipRocketLoginResponse.class).getToken());
            response.close();
        }
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isTokenExpired(String token) {
        String[] tokenParts = token.split("\\.");
        String encodedPayload = tokenParts[1];
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(encodedPayload));
        JsonParser jsonParser = new JsonParser();
        JsonObject payloadJson = jsonParser.parse(payload).getAsJsonObject();
        long expirationTime = payloadJson.get("exp").getAsLong();
        return new Date().getTime() > expirationTime;

    }
}
