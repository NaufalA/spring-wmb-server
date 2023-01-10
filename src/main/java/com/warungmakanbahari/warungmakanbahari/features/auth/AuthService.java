package com.warungmakanbahari.warungmakanbahari.features.auth;

import com.warungmakanbahari.warungmakanbahari.features.auth.dtos.LoginRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.auth.dtos.LoginResponseDto;

public interface AuthService {
    boolean validate(String token);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
