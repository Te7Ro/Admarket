package kz.guccigang.admarket.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, max = 64, message = "Password should contain more than 8 and less that 64 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password should contain at least 1 Uppercase letter, 1 Lowercase letter, 1 number and 1 symbol"
    )
    private String password;

    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "^(COMPANY|CREATOR)$", message = "Role can be COMPANY or CREATOR")
    private String role;
}
