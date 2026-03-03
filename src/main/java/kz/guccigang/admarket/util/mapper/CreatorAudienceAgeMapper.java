package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeCreateRequest;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeResponse;
import kz.guccigang.admarket.dto.creator.age.CreatorAudienceAgeUpdateRequest;
import kz.guccigang.admarket.entity.creator.CreatorAudienceAge;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {CreatorProfileMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CreatorAudienceAgeMapper {

    CreatorAudienceAgeResponse toDto(CreatorAudienceAge creatorAudienceAge);

    CreatorAudienceAge toEntity(CreatorAudienceAgeCreateRequest request);

    void updateEntity(@MappingTarget CreatorAudienceAge entity, CreatorAudienceAgeUpdateRequest request);
}
