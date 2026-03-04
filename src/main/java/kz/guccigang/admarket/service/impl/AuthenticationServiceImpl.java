package kz.guccigang.admarket.service.impl;

import kz.guccigang.admarket.dto.auth.AuthResponse;
import kz.guccigang.admarket.dto.auth.LoginRequest;
import kz.guccigang.admarket.dto.auth.RegisterRequest;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.enums.Role;
import kz.guccigang.admarket.enums.UserStatus;
import kz.guccigang.admarket.exception.auth.JwtSubjectMissingException;
import kz.guccigang.admarket.exception.auth.JwtTokenExpiredException;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.jwt.JwtFactory;
import kz.guccigang.admarket.jwt.JwtParser;
import kz.guccigang.admarket.jwt.JwtValidator;
import kz.guccigang.admarket.repository.UserRepository;
import kz.guccigang.admarket.repository.company.CompanyRepository;
import kz.guccigang.admarket.repository.creator.CreatorRepository;
import kz.guccigang.admarket.service.AuthenticationService;
import kz.guccigang.admarket.service.UserService;
import kz.guccigang.admarket.service.company.CompanyService;
import kz.guccigang.admarket.service.creator.CreatorService;
import kz.guccigang.admarket.service.email.EmailConfirmationService;
import kz.guccigang.admarket.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtFactory jwtFactory;
    private final JwtValidator jwtValidator;
    private final JwtParser jwtParser;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailConfirmationService emailConfirmationService;
    private final CompanyRepository companyRepository;
    private final CreatorRepository creatorRepository;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        userService.throwExceptionIfUserExists(request.getEmail());

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .status(UserStatus.SUSPENDED)
                .build();

        userRepository.save(user);

        if(Role.COMPANY.equals(Role.valueOf(request.getRole()))) {
            CompanyProfile companyProfile = new CompanyProfile();
            companyProfile.setUser(user);
            companyRepository.save(companyProfile);
        }
        if(Role.CREATOR.equals(Role.valueOf(request.getRole()))) {
            CreatorProfile creatorProfile = new CreatorProfile();
            creatorProfile.setUser(user);
            creatorProfile.setDisplayName(user.getEmail());
            creatorRepository.save(creatorProfile);
        }

        log.info("User saved to the database with username: {}", user.getUsername());

        emailConfirmationService.sendConfirmationCode(user);

        String accessToken = jwtFactory.generateAccessToken(user);
        String refreshToken = jwtFactory.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with email " + request.getEmail() + " not found"
                ));

        String accessToken = jwtFactory.generateAccessToken(user);
        String refreshToken = jwtFactory.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthResponse refreshToken(String authHeader) {
        String refreshToken = authHeader.substring(7);

        User user = jwtParser.extractUsername(refreshToken)
                .map(userRepository::findByEmail)
                .orElseThrow(() -> new JwtSubjectMissingException("JWT subject cannot be null"))
                .orElseThrow(() -> new EntityNotFoundException("User with this username was not found"));

        jwtValidator.ifTokenExpiredThrow(refreshToken, () -> new JwtTokenExpiredException("Refresh token has expired"));

        String accessToken = jwtFactory.generateAccessToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()
                || auth instanceof AnonymousAuthenticationToken
                || auth.getPrincipal() == null) {
            return Optional.empty();
        }

        return Optional.of((User) auth.getPrincipal());
    }
}
