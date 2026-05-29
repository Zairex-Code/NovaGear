package com.zairex_code.nova_gear.service.impl;

import com.zairex_code.nova_gear.dto.category.CategoryRequestDTO;
import com.zairex_code.nova_gear.dto.category.CategoryResponseDto;
import com.zairex_code.nova_gear.entity.Category;
import com.zairex_code.nova_gear.exception.ResourceNotFoundException;
import com.zairex_code.nova_gear.mapper.CategoryMapper;
import com.zairex_code.nova_gear.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
     public CategoryResponseDto createCategory(CategoryRequestDTO request){

        Category categoryToSave = categoryMapper.toEntity(request);
        CategoryResponseDto categoryFound = getCategoryByName(categoryToSave.getName());
        if (categoryFound == null){
            Category categorySaved = categoryRepository.save(categoryToSave);
            return categoryMapper.toResponse(categorySaved);
        }else {
            return null;
        }


     }

    @Override
    public List<CategoryResponseDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse) //We convert all categories to CategoryResponse
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryByName(String name){
        return getAllCategories().stream()
                .filter(category -> category.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Category with name " + name + "doesn't found"));
    }

}
