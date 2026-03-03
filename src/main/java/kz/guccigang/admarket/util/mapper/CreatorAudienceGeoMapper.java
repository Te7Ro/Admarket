package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoCreateRequest;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoResponse;
import kz.guccigang.admarket.dto.creator.geo.CreatorAudienceGeoUpdateRequest;
import kz.guccigang.admarket.entity.creator.CreatorAudienceGeo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {CreatorProfileMapper.class, CountryMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CreatorAudienceGeoMapper {

    CreatorAudienceGeoResponse toDto(CreatorAudienceGeo creatorAudienceGeo);

    CreatorAudienceGeo toEntity(CreatorAudienceGeoCreateRequest request);

    void updateEntity(@MappingTarget CreatorAudienceGeo entity, CreatorAudienceGeoUpdateRequest request2);
}
