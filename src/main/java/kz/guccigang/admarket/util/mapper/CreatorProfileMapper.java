package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.creator.CreatorCreateRequest;
import kz.guccigang.admarket.dto.creator.CreatorResponse;
import kz.guccigang.admarket.dto.creator.CreatorUpdateRequest;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CreatorProfileMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "primaryCategoryName", source = "primaryCategory.name")
    CreatorResponse toDto(CreatorProfile entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "primaryCategory", ignore = true)
    CreatorProfile toEntity(CreatorCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "primaryCategory", ignore = true)
    void updateEntity(CreatorUpdateRequest request, @MappingTarget CreatorProfile entity);
}
