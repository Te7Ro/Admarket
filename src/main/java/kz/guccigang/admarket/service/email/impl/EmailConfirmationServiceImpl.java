package kz.guccigang.admarket.service.email.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.email.EmailConfirmationCode;
import kz.guccigang.admarket.enums.UserStatus;
import kz.guccigang.admarket.repository.email.EmailConfirmationCodeRepository;
import kz.guccigang.admarket.service.UserService;
import kz.guccigang.admarket.service.email.EmailConfirmationService;
import kz.guccigang.admarket.service.email.EmailService;
import kz.guccigang.admarket.util.ConfirmationCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
    private final EmailConfirmationCodeRepository repository;
    private final EmailService emailService;
    private final ConfirmationCodeGenerator generator;

    @Transactional
    public void sendConfirmationCode(User user) {

        String code = generator.generateCode();

        EmailConfirmationCode entity = new EmailConfirmationCode();
        entity.setUser(user);
        entity.setCode(code);
        entity.setExpiresAt(ZonedDateTime.now().plusMinutes(10));
        entity.setUsed(false);

        repository.save(entity);

        emailService.sendConfirmationEmail(user.getEmail(), code);
    }

    @Transactional
    public void sendForgetCode(User user) {

        String code = generator.generateCode();

        EmailConfirmationCode entity = new EmailConfirmationCode();
        entity.setUser(user);
        entity.setCode(code);
        entity.setExpiresAt(ZonedDateTime.now().plusMinutes(10));
        entity.setUsed(false);

        repository.save(entity);

        emailService.sendForgetPasswordEmail(user.getEmail(), code);
    }

    public boolean confirmCode(User user, String code) {

        EmailConfirmationCode entity = repository
                .findByUserAndCodeAndUsedFalse(user, code)
                .orElseThrow(() -> new RuntimeException("Invalid code"));

        if (entity.getExpiresAt().isBefore(ZonedDateTime.now())) {
            return false;
        }

        entity.setUsed(true);
        repository.save(entity);

        return true;
    }
}
