package kz.guccigang.admarket.dto.creator.age;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatorAudienceAgeUpdateRequest {

    @Min(value = 0, message = "Age begin must be >= 0")
    @Max(value = 120, message = "Age begin must be <= 120")
    private Integer ageBegin;

    @Min(value = 0, message = "Age end must be >= 0")
    @Max(value = 120, message = "Age end must be <= 120")
    private Integer ageEnd;

    @DecimalMin(value = "0.00", inclusive = false, message = "Percentage must be > 0")
    @DecimalMax(value = "100.00", message = "Percentage must be <= 100")
    @Digits(integer = 3, fraction = 2, message = "Percentage must have max 2 decimal places")
    private BigDecimal percentage;
}
