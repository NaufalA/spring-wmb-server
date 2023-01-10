package com.warungmakanbahari.warungmakanbahari.features.auth.interceptors;

import com.warungmakanbahari.warungmakanbahari.features.auth.AuthService;
import com.warungmakanbahari.warungmakanbahari.shared.constants.UrlMapping;
import com.warungmakanbahari.warungmakanbahari.shared.exceptions.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final AuthService authService;

    public AuthInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains(UrlMapping.AUTH_URL)) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            throw new UnauthorizedException("Token is Missing");
        }
        String[] token = authHeader.split(" ");

        return authService.validate(token[1]);
    }
}
