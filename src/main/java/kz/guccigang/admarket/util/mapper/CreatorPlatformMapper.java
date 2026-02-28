package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformCreateRequest;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformResponse;
import kz.guccigang.admarket.dto.creator.platform.CreatorPlatformUpdateRequest;
import kz.guccigang.admarket.entity.creator.CreatorPlatform;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {CreatorProfileMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CreatorPlatformMapper {

    CreatorPlatformResponse toDto(CreatorPlatform platform);

    CreatorPlatform toEntity(CreatorPlatformCreateRequest request);

    void updateEntity(@MappingTarget CreatorPlatform platform, CreatorPlatformUpdateRequest request);
}
