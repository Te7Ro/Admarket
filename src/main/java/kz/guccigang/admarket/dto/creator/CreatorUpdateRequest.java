package kz.guccigang.admarket.dto.creator;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatorUpdateRequest {

    @Size(max = 255, message = "Display name must be at most 255 characters")
    private String displayName;

    @Size(max = 2000, message = "Bio must be at most 2000 characters")
    private String bio;

    private Long primaryCategoryId;

    @Min(value = 0, message = "Followers count must be >= 0")
    private Integer followersCount;

    @Min(value = 0, message = "Average views must be >= 0")
    private Integer avgViews;

    @DecimalMin(value = "0.00", message = "Engagement rate must be >= 0.00")
    @DecimalMax(value = "100.00", message = "Engagement rate must be <= 100.00")
    private BigDecimal engagementRate;

    @Email(message = "Contact email must be a valid email address")
    @Size(max = 255, message = "Contact email must be at most 255 characters")
    private String contactEmail;
}
