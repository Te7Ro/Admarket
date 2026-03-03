package kz.guccigang.admarket.dto.creator.geo;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatorAudienceGeoCreateRequest {
    @NotNull(message = "Country id is required")
    private Long countryId;

    @NotNull(message = "Percentage is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Percentage must be > 0")
    @DecimalMax(value = "100.00", message = "Percentage must be <= 100")
    @Digits(integer = 3, fraction = 2, message = "Percentage must have max 2 decimal places")
    private BigDecimal percentage;
}
