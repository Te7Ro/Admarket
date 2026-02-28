package kz.guccigang.admarket.service;

import kz.guccigang.admarket.dto.category.CategoryCreateRequest;
import kz.guccigang.admarket.dto.category.CategoryResponse;
import kz.guccigang.admarket.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse getCategoryByName(String name);
    CategoryResponse createCategory(CategoryCreateRequest request);
    Category getCategoryEntityById(Long id);
    void deleteCategory(Long id);
}
