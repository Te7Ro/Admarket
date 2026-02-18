package kz.guccigang.admarket.dto.creator;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatorCreateRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Display name is required")
    @Size(max = 255, message = "Display name must be at most 255 characters")
    private String displayName;

    @Size(max = 2000, message = "Bio must be at most 2000 characters")
    private String bio;

    @NotNull(message = "Primary category ID is required")
    private Long primaryCategoryId;

    @NotNull(message = "Followers count is required")
    @Min(value = 0, message = "Followers count must be >= 0")
    private Integer followersCount;

    @NotNull(message = "Average views is required")
    @Min(value = 0, message = "Average views must be >= 0")
    private Integer avgViews;

    @NotNull(message = "Engagement rate is required")
    @DecimalMin(value = "0.00", message = "Engagement rate must be >= 0.00")
    @DecimalMax(value = "100.00", message = "Engagement rate must be <= 100.00")
    private BigDecimal engagementRate;

    @Email(message = "Contact email must be a valid email address")
    @Size(max = 255, message = "Contact email must be at most 255 characters")
    private String contactEmail;
}
