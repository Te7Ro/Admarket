package kz.guccigang.admarket.dto.company;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyCreateRequest {

    @NotNull
    private Long userId;

    @NotBlank(message = "Company name is required")
    @Size(max = 255)
    private String companyName;

    @NotNull(message = "Industry ID is required")
    private Long industryId;

    @NotBlank(message = "Description is required")
    private String description;

    @Size(max = 512)
    @Pattern(
            regexp = "^(https?://).*$",
            message = "Website must start with http:// or https://"
    )
    private String websiteUrl;

    @NotNull(message = "Country ID is required")
    private Long countryId;

    @NotNull(message = "Min budget is required")
    @DecimalMin(value = "0.0")
    private BigDecimal minBudget;

    @NotNull(message = "Max budget is required")
    @DecimalMin(value = "0.0")
    private BigDecimal maxBudget;
}