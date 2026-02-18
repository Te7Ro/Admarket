package kz.guccigang.admarket.exception.auth;

import org.springframework.http.HttpStatus;

public class JwtTokenExpiredException extends ApiAuthenticationException {

    public JwtTokenExpiredException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}