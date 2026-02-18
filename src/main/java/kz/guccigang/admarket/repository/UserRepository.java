package kz.guccigang.admarket.repository;

import kz.guccigang.admarket.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
}
