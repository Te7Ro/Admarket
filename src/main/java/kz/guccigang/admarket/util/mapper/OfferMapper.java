package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.offer.OfferCreateRequest;
import kz.guccigang.admarket.dto.offer.OfferResponse;
import kz.guccigang.admarket.dto.offer.OfferUpdateRequest;
import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.service.offer.OfferTargetCountryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {CompanyProfileMapper.class, CategoryMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class OfferMapper {

    @Autowired
    protected OfferTargetCountryService offerTargetCountriesService;

    public abstract OfferResponse toDto(Offer offer);

    @Mapping(target = "category", ignore = true)
    public abstract Offer toEntity(OfferCreateRequest request);

    @Mapping(target = "category", ignore = true)
    public abstract void updateEntity(OfferUpdateRequest request,
                                      @MappingTarget Offer offer);

    @AfterMapping
    protected void setTargetCountries(Offer offer,
                                      @MappingTarget OfferResponse response) {

        response.setTargetCountries(
                offerTargetCountriesService.getOfferTargetCountries(offer)
        );
    }
}
