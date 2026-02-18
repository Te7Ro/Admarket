package kz.guccigang.admarket.config.security.filters;

import io.jsonwebtoken.JwtException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.guccigang.admarket.exception.auth.ApiAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {
        AuthenticationException authenticationException;

        try {
            filterChain.doFilter(request, response);
            return;
        } catch (JwtException exception) {
            authenticationException = new ApiAuthenticationException(
                    exception.getMessage(),
                    HttpStatus.UNAUTHORIZED
            );
        } catch (AuthenticationException  exception) {
            authenticationException = exception;
        }

        authenticationEntryPoint.commence(
                request,
                response,
                authenticationException)
        ;
    }

}

