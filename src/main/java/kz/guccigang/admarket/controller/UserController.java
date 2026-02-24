package kz.guccigang.admarket.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kz.guccigang.admarket.dto.user.*;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserResponse getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.getById(user.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/confirm")
    public UserResponse confirmUser(@RequestBody UserConfirmRequest request){
        return userService.confirmUser(request);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/forget-password-code")
    public ResponseEntity<Void> sendForgetPasswordCode(){
        userService.sendForgetPasswordCode();
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/forget-password")
    public ResponseEntity<UserResponse> forgetPassword(@RequestBody ForgetPasswordRequest request){
        return ResponseEntity.ok(userService.forgetPassword(request));
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id == principal.id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(
            @RequestBody @Valid UserCreateRequest request
    ) {
        return userService.create(request);
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id == principal.id")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(
            @PathVariable @Positive(message = "Id must be positive") long id,
            @RequestBody @Valid UserUpdateRequest request
    ) {
        return userService.update(id, request);
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id == principal.id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        userService.delete(id);
    }
}
