package kz.guccigang.admarket.dto.creator.platform;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kz.guccigang.admarket.enums.Platform;
import lombok.Data;

@Data
public class CreatorPlatformCreateRequest {
    @NotBlank
    private Platform platform;

    @NotBlank
    private String profileUrl;

    @NotNull
    @Min(value = 0)
    private Integer followersCount;

    @NotNull
    @Min(value = 0)
    private Integer avgViews;
}
