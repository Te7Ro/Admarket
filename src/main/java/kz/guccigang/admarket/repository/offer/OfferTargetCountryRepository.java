package kz.guccigang.admarket.repository.offer;

import kz.guccigang.admarket.dto.offer.OfferTargetCountryResponse;
import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.entity.offer.OfferTargetCountry;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferTargetCountryRepository extends BaseRepository<OfferTargetCountry> {
    List<OfferTargetCountry> findAllByOffer(Offer offer);
    Optional<OfferTargetCountry> findByOfferAndCountry(Offer offer, Country country);
    boolean existsByOfferAndCountry(Offer offer, Country country);
}
