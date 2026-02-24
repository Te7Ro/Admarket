package kz.guccigang.admarket.service;

import kz.guccigang.admarket.dto.user.*;
import kz.guccigang.admarket.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> getAll(Pageable pageable);
    UserResponse getById(long id);
    UserResponse create(UserCreateRequest requestDto);
    UserResponse update(long id, UserUpdateRequest requestDto);
    void delete(long id);
    User getEntityById(long id);
    void throwExceptionIfUserExists(String username);
    UserResponse confirmUser(UserConfirmRequest requestDto);
    void sendForgetPasswordCode();
    UserResponse forgetPassword(ForgetPasswordRequest requestDto);
}
