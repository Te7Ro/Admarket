package kz.guccigang.admarket.dto.offer;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OfferCreateRequest {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotNull(message = "Category id is required")
    private Long categoryId;

    @NotNull(message = "Budget is required")
    @DecimalMin(value = "0.01", message = "Budget must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Budget must have max 10 integer digits and 2 decimal places")
    private BigDecimal budget;

    @NotBlank(message = "Currency is required")
    @Size(min = 3, max = 3, message = "Currency must be 3 letters (ISO format)")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be in ISO format (e.g. USD, EUR, KZT)")
    private String currency;

    @NotNull(message = "Target minimum age is required")
    @Min(value = 0, message = "Minimum age cannot be negative")
    @Max(value = 120, message = "Minimum age is too large")
    private Integer targetMinAge;

    @NotNull(message = "Target maximum age is required")
    @Min(value = 0, message = "Maximum age cannot be negative")
    @Max(value = 120, message = "Maximum age is too large")
    private Integer targetMaxAge;

    @FutureOrPresent(message = "Campaign start date must be today or in the future")
    private LocalDate campaignStartDate;

    @Future(message = "Campaign end date must be in the future")
    private LocalDate campaignEndDate;
}
