package kz.guccigang.admarket.service.creator;

import kz.guccigang.admarket.dto.creator.CreatorCreateRequest;
import kz.guccigang.admarket.dto.creator.CreatorResponse;
import kz.guccigang.admarket.dto.creator.CreatorUpdateRequest;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CreatorService {
    Page<CreatorResponse> getAll(Pageable pageable);
    CreatorResponse getById(Long id);
    CreatorResponse getByUserId(Long userId);
    CreatorResponse createCreatorProfile(CreatorCreateRequest request);
    CreatorResponse updateCreatorProfile(Long id, CreatorUpdateRequest request);
    CreatorProfile getEntityByUser(User user);
    CreatorProfile getEntityById(Long id);
    void deleteCreatorProfile(Long id);
    void save(CreatorProfile profile);
}
