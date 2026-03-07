package kz.guccigang.admarket.service.offer.impl;

import kz.guccigang.admarket.dto.offer.OfferCreateRequest;
import kz.guccigang.admarket.dto.offer.OfferResponse;
import kz.guccigang.admarket.dto.offer.OfferUpdateRequest;
import kz.guccigang.admarket.entity.Category;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.enums.OfferStatus;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.offer.OfferRepository;
import kz.guccigang.admarket.service.AuthenticationService;
import kz.guccigang.admarket.service.CategoryService;
import kz.guccigang.admarket.service.company.CompanyService;
import kz.guccigang.admarket.service.offer.OfferService;
import kz.guccigang.admarket.util.mapper.OfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final AuthenticationService authService;
    private final CompanyService companyService;
    private final OfferMapper offerMapper;
    private final CategoryService categoryService;

    public Page<OfferResponse> getAllOffers(Pageable pageable){
        return offerRepository.findAllByStatusIn(List.of(OfferStatus.ACTIVE), pageable)
                .map(offerMapper::toDto);
    }

    public Page<OfferResponse> getOffersByCompany(Long companyId, Pageable pageable){
        CompanyProfile company = companyService.getEntityById(companyId);
        return offerRepository.findAllByCompanyAndStatusIn(company,List.of(OfferStatus.ACTIVE), pageable)
                .map(offerMapper::toDto);
    }

    public Page<OfferResponse> getMyOffers(Pageable pageable){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        CompanyProfile company = companyService.getEntityByUser(user);
        return offerRepository.findAllByCompany(company, pageable)
                .map(offerMapper::toDto);
    }

    public OfferResponse getOfferById(Long offerId) {
        return offerRepository.findById(offerId).map(offerMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));
    }

    public Offer getEntityById(Long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));
    }

    @Transactional
    public OfferResponse createOffer(OfferCreateRequest request) {
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CompanyProfile company = companyService.getEntityByUser(user);
        Category category = categoryService.getCategoryEntityById(request.getCategoryId());

        Offer offer = offerMapper.toEntity(request);
        offer.setCompany(company);
        offer.setCategory(category);
        offer.setStatus(OfferStatus.DRAFT);

        offerRepository.save(offer);


        return offerMapper.toDto(offer);
    }

    @Transactional
    public OfferResponse updateOfferStatus(Long offerId, OfferStatus status) {
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CompanyProfile company = companyService.getEntityByUser(user);
        Offer offer = offerRepository.findByCompanyAndId(company, offerId)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));

        offer.setStatus(status);
        offerRepository.save(offer);

        return offerMapper.toDto(offer);
    }

    @Transactional
    public OfferResponse updateOffer(Long offerId, OfferUpdateRequest request) {
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CompanyProfile company = companyService.getEntityByUser(user);
        Offer offer = offerRepository.findByCompanyAndId(company, offerId)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));

        offerMapper.updateEntity(request, offer);

        if(request.getCategoryId() != null) {
            Category category = categoryService.getCategoryEntityById(request.getCategoryId());
            if(category != null) {
                offer.setCategory(category);
            }
        }

        offerRepository.save(offer);

        return offerMapper.toDto(offer);
    }

    @Transactional
    public void deleteOffer(Long offerId) {
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CompanyProfile company = companyService.getEntityByUser(user);
        Offer offer = offerRepository.findByCompanyAndId(company, offerId)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));
        offerRepository.delete(offer);
    }
}
