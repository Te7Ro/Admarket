package kz.guccigang.admarket.service.offer;

import kz.guccigang.admarket.dto.offer.OfferTargetCountryResponse;
import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.entity.offer.Offer;

import java.util.List;

public interface OfferTargetCountryService {
    List<OfferTargetCountryResponse> getOfferTargetCountries(Long offerId);
    List<OfferTargetCountryResponse> getOfferTargetCountries(Offer offer);
    List<OfferTargetCountryResponse> addOfferTargetCountry(Long offerId, Country country);
    List<OfferTargetCountryResponse> removeOfferTargetCountry(Long offerId, Country country);
}
