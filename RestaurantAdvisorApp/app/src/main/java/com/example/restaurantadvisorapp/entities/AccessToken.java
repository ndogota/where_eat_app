package com.example.restaurantadvisorapp.entities;

import com.squareup.moshi.Json;

public class AccessToken {
    @Json(name = "token")
    String token;

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }
}
