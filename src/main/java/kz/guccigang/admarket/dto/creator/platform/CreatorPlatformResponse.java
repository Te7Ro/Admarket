package kz.guccigang.admarket.dto.creator.platform;

import kz.guccigang.admarket.dto.creator.CreatorResponse;
import kz.guccigang.admarket.enums.Platform;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CreatorPlatformResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private CreatorResponse creator;
    private Platform platform;
    private String profileUrl;
    private Integer followersCount;
    private Integer avgViews;
}
