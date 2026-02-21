package kz.guccigang.admarket.repository.offer;

import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.entity.offer.OfferTargetCountry;
import kz.guccigang.admarket.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferTargetCountryRepository extends BaseRepository<OfferTargetCountry> {
    List<OfferTargetCountry> findByOffer(Offer offer);
}
