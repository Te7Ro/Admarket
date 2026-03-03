package kz.guccigang.admarket.service.creator;

import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoCreateRequest;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoResponse;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoUpdateRequest;

import java.util.List;

public interface CreatorAudienceGeoService {
    List<CreatorAudienceGeoResponse> getAudienceGeoByCreatorId(Long creatorId);
    CreatorAudienceGeoResponse createAudienceGeo(CreatorAudienceGeoCreateRequest request);
    CreatorAudienceGeoResponse updateAudienceGeo(Long id, CreatorAudienceGeoUpdateRequest request);
    void deleteAudienceGeo(Long id);
}
