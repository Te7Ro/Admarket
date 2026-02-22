package kz.guccigang.admarket.service.email.impl;

import kz.guccigang.admarket.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    public void sendConfirmationEmail(String to, String code){
        try {
            String subject = "Подтверждение регистрации в AdMarket";

            String content = """
                    Здравствуйте!
                    
                    Спасибо за регистрацию в AdMarket.
                    
                    Ваш код подтверждения:
                    
                    %s
                    
                    Код действителен в течение 10 минут.
                    
                    Если вы не регистрировались, просто проигнорируйте это письмо.
                    
                    С уважением,
                    Команда AdMarket
                    """.formatted(code);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);

            mailSender.send(message);
            log.info("Send confirmation email to {}", to);
        } catch (Exception e) {
            log.error("Send confirmation email error {}", e.getMessage());
            throw new RuntimeException("Failed to send email");
        }
    }
}
