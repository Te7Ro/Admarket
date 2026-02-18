package kz.guccigang.admarket.service;

import kz.guccigang.admarket.dto.auth.AuthResponse;
import kz.guccigang.admarket.dto.auth.LoginRequest;
import kz.guccigang.admarket.dto.auth.RegisterRequest;
import kz.guccigang.admarket.entity.User;

import java.util.Optional;

public interface AuthenticationService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(LoginRequest request);
    AuthResponse refreshToken(String authHeader);
    public Optional<User> getCurrentUser();
}
