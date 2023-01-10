package com.warungmakanbahari.warungmakanbahari.features.auth.dtos;

public class JwtPayload {
    private final String email;
    private final String role;

    public JwtPayload(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "{" +
                "email:'" + email + '\'' +
                ", role:'" + role + '\'' +
                '}';
    }
}
