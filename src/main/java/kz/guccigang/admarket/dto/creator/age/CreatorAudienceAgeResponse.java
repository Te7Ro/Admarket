package kz.guccigang.admarket.dto.creator.age;

import kz.guccigang.admarket.dto.creator.CreatorResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CreatorAudienceAgeResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private CreatorResponse creator;
    private Integer ageBegin;
    private Integer ageEnd;
    private BigDecimal percentage;
}
