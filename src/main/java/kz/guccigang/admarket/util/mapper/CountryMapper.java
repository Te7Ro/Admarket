package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.country.CountryCreateRequest;
import kz.guccigang.admarket.dto.country.CountryResponse;
import kz.guccigang.admarket.dto.country.CountryUpdateRequest;
import kz.guccigang.admarket.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CountryMapper {

    CountryResponse toDto(Country country);

    Country toEntity(CountryCreateRequest request);

    void updateEntity(CountryUpdateRequest request, @MappingTarget Country country);
}
