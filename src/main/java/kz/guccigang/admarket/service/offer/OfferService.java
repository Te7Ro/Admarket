package kz.guccigang.admarket.service.offer;

import kz.guccigang.admarket.dto.offer.OfferCreateRequest;
import kz.guccigang.admarket.dto.offer.OfferResponse;
import kz.guccigang.admarket.dto.offer.OfferUpdateRequest;
import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.enums.OfferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfferService {
    Page<OfferResponse> getAllOffers(Pageable pageable);
    Page<OfferResponse> getOffersByCompany(Long companyId, Pageable pageable);
    Page<OfferResponse> getMyOffers(Pageable pageable);
    OfferResponse getOfferById(Long offerId);
    OfferResponse createOffer(OfferCreateRequest request);
    OfferResponse updateOfferStatus(Long offerId, OfferStatus status);
    OfferResponse updateOffer(Long offerId, OfferUpdateRequest request);
    Offer getEntityById(Long offerId);
    void deleteOffer(Long offerId);

}
