package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.offer.OfferTargetCountryResponse;
import kz.guccigang.admarket.entity.offer.OfferTargetCountry;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {CountryMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OfferTargetCountryMapper {
    OfferTargetCountryResponse toDto(OfferTargetCountry entity);
}
