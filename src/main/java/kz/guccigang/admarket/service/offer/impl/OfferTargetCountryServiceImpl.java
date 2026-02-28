package kz.guccigang.admarket.service.offer.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.offer.OfferTargetCountryResponse;
import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.entity.offer.OfferTargetCountry;
import kz.guccigang.admarket.exception.entity.EntityAlreadyExistsException;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.offer.OfferRepository;
import kz.guccigang.admarket.repository.offer.OfferTargetCountryRepository;
import kz.guccigang.admarket.service.offer.OfferTargetCountryService;
import kz.guccigang.admarket.util.mapper.OfferTargetCountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferTargetCountryServiceImpl implements OfferTargetCountryService {
    private final OfferTargetCountryRepository offerTargetCountryRepository;
    private final OfferTargetCountryMapper mapper;
    private final OfferRepository offerRepository;

    public List<OfferTargetCountryResponse> getOfferTargetCountries(Offer offer){
        return offerTargetCountryRepository.findAllByOffer(offer)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<OfferTargetCountryResponse> getOfferTargetCountries(Long offerId){
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));
        return getOfferTargetCountries(offer);
    }

    @Transactional
    public List<OfferTargetCountryResponse> addOfferTargetCountry(Long offerId, Country country){
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));
        if(offerTargetCountryRepository.existsByOfferAndCountry(offer, country)){
            throw new EntityAlreadyExistsException("Target country already exists");
        }
        OfferTargetCountry targetCountry = new OfferTargetCountry();
        targetCountry.setOffer(offer);
        targetCountry.setCountry(country);
        offerTargetCountryRepository.save(targetCountry);

        return  getOfferTargetCountries(offer);
    }

    @Transactional
    public List<OfferTargetCountryResponse> removeOfferTargetCountry(Long offerId, Country country){
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));
        OfferTargetCountry targetCountry = offerTargetCountryRepository.findByOfferAndCountry(offer,country)
                .orElseThrow(() -> new EntityNotFoundException("Target country not found"));
        offerTargetCountryRepository.delete(targetCountry);

        return  getOfferTargetCountries(offer);
    }
}
