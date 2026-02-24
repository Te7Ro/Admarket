package kz.guccigang.admarket.dto.user;

import lombok.Data;

@Data
public class ForgetPasswordRequest {
    private String code;
    private String newPassword;
}
