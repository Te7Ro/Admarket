package kz.guccigang.admarket.service.creator.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoCreateRequest;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoResponse;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoUpdateRequest;
import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.creator.CreatorAudienceGeo;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.creator.CreatorAudienceGeoRepository;
import kz.guccigang.admarket.repository.creator.CreatorRepository;
import kz.guccigang.admarket.service.AuthenticationService;
import kz.guccigang.admarket.service.CountryService;
import kz.guccigang.admarket.service.creator.CreatorAudienceGeoService;
import kz.guccigang.admarket.service.creator.CreatorService;
import kz.guccigang.admarket.util.mapper.CreatorAudienceGeoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatorAudienceGeoServiceImpl implements CreatorAudienceGeoService {
    private final CreatorAudienceGeoRepository repository;
    private final CreatorAudienceGeoMapper mapper;
    private final CreatorRepository creatorRepository;
    private final AuthenticationService authService;
    private final CountryService countryService;

    public List<CreatorAudienceGeoResponse> getAudienceGeoByCreatorId(Long creatorId){
        CreatorProfile creator = creatorRepository.findById(creatorId)
                .orElseThrow(() -> new EntityNotFoundException("creator not found"));
        return repository.findAllByCreator(creator)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public CreatorAudienceGeoResponse createAudienceGeo(CreatorAudienceGeoCreateRequest request){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorRepository.findByUser(user);
        CreatorAudienceGeo audienceGeo = mapper.toEntity(request);
        audienceGeo.setCreator(creator);

        Country country = countryService.getEntityById(request.getCountryId());
        audienceGeo.setCountry(country);

        repository.save(audienceGeo);
        return mapper.toDto(audienceGeo);
    }

    @Transactional
    public CreatorAudienceGeoResponse updateAudienceGeo(Long id, CreatorAudienceGeoUpdateRequest request){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorRepository.findByUser(user);
        CreatorAudienceGeo audienceGeo = repository.findByIdAndCreator(id, creator)
                .orElseThrow(() -> new EntityNotFoundException("Audience Geo not found"));
        mapper.updateEntity(audienceGeo, request);
        repository.save(audienceGeo);

        return mapper.toDto(audienceGeo);
    }

    @Transactional
    public void deleteAudienceGeo(Long id){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorRepository.findByUser(user);
        CreatorAudienceGeo audienceGeo = repository.findByIdAndCreator(id, creator)
                .orElseThrow(() -> new EntityNotFoundException("Audience Geo not found"));
        repository.delete(audienceGeo);
    }
}
