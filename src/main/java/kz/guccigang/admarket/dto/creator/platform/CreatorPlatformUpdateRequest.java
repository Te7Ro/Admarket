package kz.guccigang.admarket.dto.creator.platform;

import jakarta.validation.constraints.Min;
import kz.guccigang.admarket.enums.Platform;
import lombok.Data;

@Data
public class CreatorPlatformUpdateRequest {
    private Platform platform;

    private String profileUrl;

    @Min(value = 0)
    private Integer followersCount;

    @Min(value = 0)
    private Integer avgViews;
}
