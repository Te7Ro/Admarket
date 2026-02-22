package kz.guccigang.admarket.dto.industry;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IndustryCreateRequest {
    @NotBlank
    private String name;
}
