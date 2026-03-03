package kz.guccigang.admarket.service.creator;

import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeCreateRequest;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeResponse;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeUpdateRequest;

import java.util.List;

public interface CreatorAudienceAgeService {
    List<CreatorAudienceAgeResponse> getAudienceAgeByCreatorId(Long creatorId);
    CreatorAudienceAgeResponse createAudienceAge(CreatorAudienceAgeCreateRequest request);
    CreatorAudienceAgeResponse updateAudienceAge(Long id, CreatorAudienceAgeUpdateRequest request);
    void deleteAudienceAge(Long id);

}
