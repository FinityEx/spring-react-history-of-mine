package com.endtoend.historyOfMine.websecurity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("userId")
    private Integer userId;


    public AuthenticationResponse(String accessToken, Integer userId){
        this(accessToken, null, userId);
    }

    public AuthenticationResponse(String accessToken, @Nullable String refreshToken, Integer id) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
