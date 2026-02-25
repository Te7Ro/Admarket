package kz.guccigang.admarket.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendForgetPasswordCodeRequest {
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Incorrect email format")
    private String email;
}
