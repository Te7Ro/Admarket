package kz.guccigang.admarket.service.creator.impl;

import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformCreateRequest;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformResponse;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformUpdateRequest;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.creator.CreatorPlatform;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.creator.CreatorPlatformRepository;
import kz.guccigang.admarket.repository.creator.CreatorRepository;
import kz.guccigang.admarket.service.AuthenticationService;
import kz.guccigang.admarket.service.creator.CreatorPlatformService;
import kz.guccigang.admarket.service.creator.CreatorService;
import kz.guccigang.admarket.util.mapper.CreatorPlatformMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatorPlatformServiceImpl implements CreatorPlatformService {
    private final CreatorPlatformRepository repository;
    private final CreatorRepository creatorRepository;
    private final AuthenticationService authService;
    private final CreatorPlatformMapper mapper;

    public List<CreatorPlatformResponse> getAllCreatorPlatforms(Long creatorId){
        CreatorProfile creator = creatorRepository.findById(creatorId)
                .orElseThrow(() -> new EntityNotFoundException("creator not found"));
        return repository.findAllByCreator(creator)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public CreatorPlatformResponse createCreatorPlatform(CreatorPlatformCreateRequest request){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorRepository.findByUser(user);

        CreatorPlatform platform = mapper.toEntity(request);
        platform.setCreator(creator);
        repository.save(platform);

        return mapper.toDto(platform);
    }

    @Transactional
    public CreatorPlatformResponse updateCreatorPlatform(Long id, CreatorPlatformUpdateRequest request){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorRepository.findByUser(user);
        CreatorPlatform platform = repository.findByIdAndCreator(id, creator)
                .orElseThrow(() -> new EntityNotFoundException("Platform not found"));
        mapper.updateEntity(platform, request);

        repository.save(platform);

        return mapper.toDto(platform);
    }

    @Transactional
    public void deleteCreatorPlatform(Long id){
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        CreatorProfile creator = creatorRepository.findByUser(user);
        CreatorPlatform platform = repository.findByIdAndCreator(id, creator)
                .orElseThrow(() -> new EntityNotFoundException("Platform not found"));
        repository.delete(platform);
    }
}
