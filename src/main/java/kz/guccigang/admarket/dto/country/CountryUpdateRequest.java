package kz.guccigang.admarket.dto.country;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CountryUpdateRequest {
    private String name;
    @Size(min = 2, max = 2)
    private String code;
}
