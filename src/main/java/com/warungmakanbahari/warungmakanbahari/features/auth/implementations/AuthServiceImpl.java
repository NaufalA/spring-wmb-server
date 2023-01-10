package com.warungmakanbahari.warungmakanbahari.features.auth.implementations;

import com.warungmakanbahari.warungmakanbahari.features.auth.AuthService;
import com.warungmakanbahari.warungmakanbahari.features.auth.UserRepository;
import com.warungmakanbahari.warungmakanbahari.features.auth.dtos.JwtPayload;
import com.warungmakanbahari.warungmakanbahari.features.auth.dtos.LoginRequestDto;
import com.warungmakanbahari.warungmakanbahari.features.auth.dtos.LoginResponseDto;
import com.warungmakanbahari.warungmakanbahari.features.auth.entities.User;
import com.warungmakanbahari.warungmakanbahari.shared.exceptions.UnauthorizedException;
import com.warungmakanbahari.warungmakanbahari.shared.utils.JwtUtility;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtility jwtUtility;

    public AuthServiceImpl(UserRepository userRepository, JwtUtility jwtUtility) {
        this.userRepository = userRepository;
        this.jwtUtility = jwtUtility;
    }

    public LoginResponseDto login(LoginRequestDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

        if (user.isEmpty() || !Objects.equals(user.get().getPassword(), loginDto.getPassword())) {
            throw new UnauthorizedException("Wrong Username or Password");
        }

        String token = jwtUtility.generateToken(
                new JwtPayload(user.get().getEmail(), user.get().getUserRole().getName()).toString()
        );

        return new LoginResponseDto(token);
    }

    public boolean validate(String token) {
        return jwtUtility.validateToken(token);
    }
}
