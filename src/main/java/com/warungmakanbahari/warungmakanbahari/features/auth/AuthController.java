package com.warungmakanbahari.warungmakanbahari.features.auth;

import com.warungmakanbahari.warungmakanbahari.features.auth.dtos.LoginRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.auth.dtos.LoginResponseDto;
import com.warungmakanbahari.warungmakanbahari.features.auth.dtos.ValidateRequestDto;
import com.warungmakanbahari.warungmakanbahari.shared.constants.UrlMapping;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.CommonResponse;
import com.warungmakanbahari.warungmakanbahari.shared.dtos.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlMapping.AUTH_URL)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody LoginRequestDto requestDto) {
        LoginResponseDto response = authService.login(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK.value()),
                "Login Success",
                response
        ));
    }

    @PostMapping("/validate")
    public ResponseEntity<CommonResponse> validate(@Valid @RequestBody ValidateRequestDto loginResponseDto) {
        boolean response = authService.validate(loginResponseDto.getAccessToken());

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                String.valueOf(HttpStatus.OK.value()),
                "Login Success",
                response
        ));
    }
}
