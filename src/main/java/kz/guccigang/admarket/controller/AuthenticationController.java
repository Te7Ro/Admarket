package kz.guccigang.admarket.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import kz.guccigang.admarket.dto.auth.AuthResponse;
import kz.guccigang.admarket.dto.auth.LoginRequest;
import kz.guccigang.admarket.dto.auth.RegisterRequest;
import kz.guccigang.admarket.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse authenticate(
            @RequestBody @Valid LoginRequest request)
    {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            @Pattern(regexp = "Bearer .*", message = "Authorization header must start with 'Bearer '")
            String authHeader
    ) {
        return authenticationService.refreshToken(authHeader);
    }

}