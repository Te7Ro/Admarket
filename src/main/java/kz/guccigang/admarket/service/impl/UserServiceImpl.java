package kz.guccigang.admarket.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.guccigang.admarket.dto.user.UserCreateRequest;
import kz.guccigang.admarket.dto.user.UserResponse;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.enums.Role;
import kz.guccigang.admarket.exception.entity.EntityAlreadyExistsException;
import kz.guccigang.admarket.repository.UserRepository;
import kz.guccigang.admarket.service.UserService;
import kz.guccigang.admarket.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        user.setPasswordHash(
                passwordEncoder.encode(requestDto.getPassword())
        );

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponse update(long id, UserCreateRequest requestDto) {
        User user = getEntityById(id);

        String oldUsername = user.getUsername();
        String newUsername = requestDto.getEmail();

        if (!Objects.equals(oldUsername, newUsername)) {
            throwExceptionIfUserExists(newUsername);
            user.setEmail(newUsername);
        }

        user.setRole(
                Role.valueOf(requestDto.getRole())
        );
        user.setPasswordHash(
                passwordEncoder.encode(requestDto.getPassword())
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
    public void throwExceptionIfUserExists(String email) {
        userRepository.findByEmail(email)
                .ifPresent(foundUser -> {
                    throw new EntityAlreadyExistsException(
                            "User with the email " + foundUser.getUsername() + " already exists"
                    );
                });
    }

}
