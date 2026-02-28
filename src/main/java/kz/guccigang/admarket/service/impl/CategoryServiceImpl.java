package kz.guccigang.admarket.service.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.category.CategoryCreateRequest;
import kz.guccigang.admarket.dto.category.CategoryResponse;
import kz.guccigang.admarket.entity.Category;
import kz.guccigang.admarket.exception.entity.EntityAlreadyExistsException;
import kz.guccigang.admarket.repository.CategoryRepository;
import kz.guccigang.admarket.service.CategoryService;
import kz.guccigang.admarket.util.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAllByOrderByNameAsc()
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id){
        return categoryRepository.findById(id).map(mapper::toDto).orElse(null);
    }

    public CategoryResponse getCategoryByName(String name){
        return categoryRepository.findByName(name).map(mapper::toDto).orElse(null);
    }

    public Category getCategoryEntityById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional
    public CategoryResponse createCategory(CategoryCreateRequest request){
        if(categoryRepository.existsByName(request.getName())){
            throw new EntityAlreadyExistsException("Category already exists");
        }
        Category category = mapper.toEntity(request);
        categoryRepository.save(category);
        return mapper.toDto(category);
    }

    @Transactional
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
