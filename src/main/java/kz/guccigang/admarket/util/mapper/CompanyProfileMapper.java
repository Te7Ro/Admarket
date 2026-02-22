package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.company.CompanyCreateRequest;
import kz.guccigang.admarket.dto.company.CompanyResponse;
import kz.guccigang.admarket.dto.company.CompanyUpdateRequest;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyProfileMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "industryName", source = "industry.name")
    @Mapping(target = "country", source = "country")
    CompanyResponse toDto(CompanyProfile entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "industry", ignore = true)
    @Mapping(target = "country", ignore = true)
    CompanyProfile toEntity(CompanyCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "industry", ignore = true)
    @Mapping(target = "country", ignore = true)
    void updateEntity(CompanyUpdateRequest request, @MappingTarget CompanyProfile entity);
}
