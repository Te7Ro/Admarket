package kz.guccigang.admarket.service.creator.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.creator.CreatorCreateRequest;
import kz.guccigang.admarket.dto.creator.CreatorResponse;
import kz.guccigang.admarket.dto.creator.CreatorUpdateRequest;
import kz.guccigang.admarket.entity.Category;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.exception.entity.EntityAlreadyExistsException;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.CategoryRepository;
import kz.guccigang.admarket.repository.creator.CreatorRepository;
import kz.guccigang.admarket.repository.UserRepository;
import kz.guccigang.admarket.service.AuthenticationService;
import kz.guccigang.admarket.service.creator.CreatorAudienceAgeService;
import kz.guccigang.admarket.service.creator.CreatorAudienceGeoService;
import kz.guccigang.admarket.service.creator.CreatorPlatformService;
import kz.guccigang.admarket.service.creator.CreatorService;
import kz.guccigang.admarket.util.mapper.CreatorProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorServiceImpl implements CreatorService {
    private final CreatorRepository creatorRepository;
    private final CreatorProfileMapper mapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticationService authenticationService;
    private final CreatorPlatformService platformService;
    private final CreatorAudienceAgeService audienceAgeService;
    private final CreatorAudienceGeoService audienceGeoService;

    public CreatorResponse getById(Long id) {
        CreatorProfile profile = creatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Creator not found"));
        CreatorResponse response = mapper.toDto(profile);
        response.setPlatforms(platformService.getAllCreatorPlatforms(profile.getId()));
        response.setAudienceAges(audienceAgeService.getAudienceAgeByCreatorId(profile.getId()));
        response.setAudienceGeos(audienceGeoService.getAudienceGeoByCreatorId(profile.getId()));
        return response;
    }

    public Page<CreatorResponse> getAll(Pageable pageable) {
        return creatorRepository.findAll(pageable)
                .map(mapper::toDto);
    }

    public CreatorResponse getByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        CreatorProfile profile = creatorRepository.findByUser(user);
        CreatorResponse response = mapper.toDto(profile);
        response.setPlatforms(platformService.getAllCreatorPlatforms(profile.getId()));
        response.setAudienceAges(audienceAgeService.getAudienceAgeByCreatorId(profile.getId()));
        response.setAudienceGeos(audienceGeoService.getAudienceGeoByCreatorId(profile.getId()));
        return response;
    }

    public CreatorProfile getEntityById(Long id){
        return creatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Creator not found"));
    }

    public CreatorProfile getEntityByUser(User user) {
        return creatorRepository.findByUser(user);
    }

    @Transactional
    public CreatorResponse createCreatorProfile(CreatorCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        Category category = categoryRepository.findById(request.getPrimaryCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category Not Found"));

        if(creatorRepository.existsByUser(user))
            throw new EntityAlreadyExistsException("Creator already exists");

        CreatorProfile profile = mapper.toEntity(request);
        profile.setUser(user);
        profile.setPrimaryCategory(category);

        creatorRepository.save(profile);
        return mapper.toDto(profile);
    }

    @Transactional
    public CreatorResponse updateCreatorProfile(Long id, CreatorUpdateRequest request) {
        User user = authenticationService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        CreatorProfile creatorProfile = creatorRepository.findByUser(user);

        mapper.updateEntity(request, creatorProfile);

        if(request.getPrimaryCategoryId() != null){
            Category category = categoryRepository.findById(request.getPrimaryCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category Not Found"));
            creatorProfile.setPrimaryCategory(category);
        }

        creatorRepository.save(creatorProfile);
        return mapper.toDto(creatorProfile);
    }

    @Transactional
    public void deleteCreatorProfile(Long id) {
        creatorRepository.deleteById(id);
    }

    @Transactional
    public void save(CreatorProfile profile) {
        creatorRepository.save(profile);
    }

}
