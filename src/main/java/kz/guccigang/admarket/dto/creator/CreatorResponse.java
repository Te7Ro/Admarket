package kz.guccigang.admarket.dto.creator;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CreatorResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Long userId;
    private String displayName;
    private String bio;
    private String primaryCategoryName;
    private Integer followersCount;
    private Integer avgViews;
    private BigDecimal engagementRate;
    private String contactEmail;
}
