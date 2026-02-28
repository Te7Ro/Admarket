package kz.guccigang.admarket.service.creator;

import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformCreateRequest;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformResponse;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformUpdateRequest;

import java.util.List;

public interface CreatorPlatformService {
    List<CreatorPlatformResponse> getAllCreatorPlatforms(Long creatorId);
    CreatorPlatformResponse createCreatorPlatform(CreatorPlatformCreateRequest request);
    CreatorPlatformResponse updateCreatorPlatform(Long id, CreatorPlatformUpdateRequest request);
    void deleteCreatorPlatform(Long id);
}
