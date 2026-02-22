package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.Category.CategoryCreateRequest;
import kz.guccigang.admarket.dto.Category.CategoryResponse;
import kz.guccigang.admarket.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryResponse toDto(Category category);

    Category toEntity(CategoryCreateRequest request);
}
