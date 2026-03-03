package kz.guccigang.admarket.service.creator.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeCreateRequest;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeResponse;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeUpdateRequest;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.creator.CreatorAudienceAge;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.creator.CreatorAudienceAgeRepository;
import kz.guccigang.admarket.service.AuthenticationService;
import kz.guccigang.admarket.service.creator.CreatorAudienceAgeService;
import kz.guccigang.admarket.service.creator.CreatorService;
import kz.guccigang.admarket.util.mapper.CreatorAudienceAgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatorAudienceAgeServiceImpl implements CreatorAudienceAgeService {
    private final CreatorAudienceAgeRepository repository;
    private final CreatorAudienceAgeMapper mapper;
    private final CreatorService creatorService;
    private final AuthenticationService authService;

    public List<CreatorAudienceAgeResponse> getAudienceAgeByCreatorId(Long creatorId){
        CreatorProfile creator = creatorService.getEntityById(creatorId);
        return repository.findAllByCreator(creator)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public CreatorAudienceAgeResponse createAudienceAge(CreatorAudienceAgeCreateRequest request){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorService.getEntityByUser(user);

        CreatorAudienceAge audienceAge = mapper.toEntity(request);
        audienceAge.setCreator(creator);

        repository.save(audienceAge);

        return mapper.toDto(audienceAge);
    }

    @Transactional
    public CreatorAudienceAgeResponse updateAudienceAge(Long id, CreatorAudienceAgeUpdateRequest request){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorService.getEntityByUser(user);
        CreatorAudienceAge creatorAudienceAge = repository.findByIdAndCreator(id, creator)
                .orElseThrow(()-> new EntityNotFoundException("AudienceAge not found"));
        mapper.updateEntity(creatorAudienceAge, request);
        repository.save(creatorAudienceAge);

        return mapper.toDto(creatorAudienceAge);
    }

    @Transactional
    public void deleteAudienceAge(Long id){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorService.getEntityByUser(user);
        CreatorAudienceAge creatorAudienceAge = repository.findByIdAndCreator(id, creator)
                .orElseThrow(()-> new EntityNotFoundException("AudienceAge not found"));
        repository.delete(creatorAudienceAge);
    }
}
