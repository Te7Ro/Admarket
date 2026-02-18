package kz.guccigang.admarket.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, max = 64, message = "Password should contain more than 8 and less that 64 characters")
    private String password;
}
