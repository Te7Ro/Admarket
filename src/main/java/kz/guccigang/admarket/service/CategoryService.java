package kz.guccigang.admarket.service;

import kz.guccigang.admarket.dto.Category.CategoryCreateRequest;
import kz.guccigang.admarket.dto.Category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse getCategoryByName(String name);
    CategoryResponse createCategory(CategoryCreateRequest request);
    void deleteCategory(Long id);
}
