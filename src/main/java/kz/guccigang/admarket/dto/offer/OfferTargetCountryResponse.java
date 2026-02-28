package kz.guccigang.admarket.dto.offer;

import kz.guccigang.admarket.dto.country.CountryResponse;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OfferTargetCountryResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private CountryResponse country;
}
