package kz.guccigang.admarket.dto.creator;

import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeResponse;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoResponse;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

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
    private List<CreatorPlatformResponse> platforms;
    private List<CreatorAudienceAgeResponse> audienceAges;
    private List<CreatorAudienceGeoResponse> audienceGeos;
}
