package kz.guccigang.admarket.service.email;

public interface EmailService {
    void sendConfirmationEmail(String to, String code);
    void sendForgetPasswordEmail(String to, String code);
}
