package kz.guccigang.admarket.service.email;

public interface EmailService {
    void sendConfirmationEmail(String to, String code);
}
