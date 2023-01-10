package com.warungmakanbahari.warungmakanbahari.features.auth.dtos;

public class LoginResponseDto {
    private final String accessToken;

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
