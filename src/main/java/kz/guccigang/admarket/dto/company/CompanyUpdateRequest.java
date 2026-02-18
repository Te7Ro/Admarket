package kz.guccigang.admarket.dto.company;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyUpdateRequest {

    @Size(max = 255)
    private String companyName;

    private Long industryId;

    private String description;

    @Size(max = 512)
    @Pattern(
            regexp = "^(https?://).*$",
            message = "Website must start with http:// or https://"
    )
    private String websiteUrl;

    private Long countryId;

    @DecimalMin(value = "0.0")
    private BigDecimal minBudget;

    @DecimalMin(value = "0.0")
    private BigDecimal maxBudget;
}
