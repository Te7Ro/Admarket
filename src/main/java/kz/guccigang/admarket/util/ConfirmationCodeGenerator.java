package kz.guccigang.admarket.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ConfirmationCodeGenerator {

    public String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

}