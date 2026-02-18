package kz.guccigang.admarket.dto.user;

import kz.guccigang.admarket.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    Long id;
    String email;
    Role role;
}
