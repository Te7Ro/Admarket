package kz.guccigang.admarket.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.guccigang.admarket.dto.user.*;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.enums.UserStatus;
import kz.guccigang.admarket.exception.entity.EntityAlreadyExistsException;
import kz.guccigang.admarket.repository.UserRepository;
import kz.guccigang.admarket.service.UserService;
import kz.guccigang.admarket.service.email.EmailConfirmationService;
import kz.guccigang.admarket.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailConfirmationService emailConfirmationService;

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::toDto);
    }

    @Override
    public UserResponse getById(long id) {
        User user = getEntityById(id);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponse create(UserCreateRequest requestDto) {
        throwExceptionIfUserExists(requestDto.getEmail());

        User user = userMapper.toEntity(requestDto);
        user.setPassword(
                passwordEncoder.encode(requestDto.getPassword())
        );

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponse update(long id, UserUpdateRequest requestDto) {
        User user = getEntityById(id);

        String oldPassword = user.getPassword();
        String requestEnctyptedOldPassword = passwordEncoder.encode(requestDto.getOldPassword());

        if(!Objects.equals(oldPassword, requestEnctyptedOldPassword)) {
            throw new IllegalArgumentException("Old password does not match");
        }
        user.setPassword(
                passwordEncoder.encode(requestDto.getNewPassword())
        );

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = getEntityById(id);
        userRepository.delete(user);
    }

    @Override
    public User getEntityById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " does not exist"));
    }

    @Override
    public User getEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " does not exist"));
    }

    @Override
    public void throwExceptionIfUserExists(String email) {
        userRepository.findByEmail(email)
                .ifPresent(foundUser -> {
                    throw new EntityAlreadyExistsException(
                            "User with the email " + foundUser.getUsername() + " already exists"
                    );
                });
    }
    public String getUserIdFromSecurityContext(){
        String subject = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("UserId: {}", subject);
        return subject;
    }

    public User getMyUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UserResponse getMe() {
        return userMapper.toDto(getMyUser());
    }


    @Transactional
    public UserResponse confirmUser(UserConfirmRequest requestDto) {
        User user = this.getMyUser();

        if(emailConfirmationService.confirmCode(user.getEmail(), requestDto.getCode())){
            user.setStatus(UserStatus.ACTIVE);
        } else {
            throw new IllegalArgumentException("Code does not match");
        }

        return userMapper.toDto(user);
    }

    public void sendForgetPasswordCode(SendForgetPasswordCodeRequest request){
        emailConfirmationService.sendForgetCode(request.getEmail());
    }

    @Transactional
    public UserResponse forgetPassword(ForgetPasswordRequest request){
        User user = this.getEntityByEmail(request.getEmail());

        if(emailConfirmationService.confirmCode(request.getEmail(), request.getCode())){
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        } else {
            throw new IllegalArgumentException("Code does not match");
        }

        return userMapper.toDto(user);
    }
}
