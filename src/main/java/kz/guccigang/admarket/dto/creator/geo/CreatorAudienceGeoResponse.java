package kz.guccigang.admarket.dto.creator.geo;

import kz.guccigang.admarket.dto.country.CountryResponse;
import kz.guccigang.admarket.dto.creator.CreatorResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CreatorAudienceGeoResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private CreatorResponse creator;
    private CountryResponse country;
    private BigDecimal percentage;
}
