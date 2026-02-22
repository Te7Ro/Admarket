package kz.guccigang.admarket.repository.email;

import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.email.EmailConfirmationCode;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmationCodeRepository extends BaseRepository<EmailConfirmationCode> {
    Optional<EmailConfirmationCode> findByUserAndCodeAndUsedFalse(User user, String code);
}
