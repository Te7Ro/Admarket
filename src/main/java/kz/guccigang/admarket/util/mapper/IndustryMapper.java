package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.industry.IndustryCreateRequest;
import kz.guccigang.admarket.dto.industry.IndustryResponse;
import kz.guccigang.admarket.entity.company.Industry;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IndustryMapper {

    IndustryResponse toDto(Industry industry);

    Industry toEntity(IndustryCreateRequest request);
}
