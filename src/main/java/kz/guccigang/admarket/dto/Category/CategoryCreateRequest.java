package kz.guccigang.admarket.dto.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreateRequest {
    @NotBlank
    private String name;
}
