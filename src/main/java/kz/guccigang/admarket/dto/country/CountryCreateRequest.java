package kz.guccigang.admarket.dto.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CountryCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 2, max = 2)
    private String code;
}
