package kz.guccigang.admarket.service.creator;

import kz.guccigang.admarket.dto.creator.CreatorCreateRequest;
import kz.guccigang.admarket.dto.creator.CreatorResponse;
import kz.guccigang.admarket.dto.creator.CreatorUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CreatorService {
    Page<CreatorResponse> getAll(Pageable pageable);
    CreatorResponse getById(Long id);
    CreatorResponse createCreatorProfile(CreatorCreateRequest request);
    CreatorResponse updateCreatorProfile(Long id, CreatorUpdateRequest request);
    void deleteCreatorProfile(Long id);
}
