package kz.guccigang.admarket.service.email;

import kz.guccigang.admarket.entity.User;

public interface EmailConfirmationService {
    void sendConfirmationCode(User user);
    void sendForgetCode(User user);
    boolean confirmCode(User user, String code);
}
