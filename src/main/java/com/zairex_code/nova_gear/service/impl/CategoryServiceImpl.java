package com.zairex_code.nova_gear.service.impl;

import com.zairex_code.nova_gear.dto.category.CategoryRequestDTO;
import com.zairex_code.nova_gear.dto.category.CategoryResponseDto;
import com.zairex_code.nova_gear.dto.product.ProductRequestDTO;
import com.zairex_code.nova_gear.entity.Category;
import com.zairex_code.nova_gear.exception.ResourceNotFoundException;
import com.zairex_code.nova_gear.mapper.CategoryMapper;
import com.zairex_code.nova_gear.repository.CategoryRepository;
import com.zairex_code.nova_gear.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
     public CategoryResponseDto createCategory(CategoryRequestDTO request){

        if (categoryRepository.existsByName(request.name())){
            throw new RuntimeException("This category "+ request.name()+" already exist");
        }
        Category categoryToSave = categoryMapper.toEntity(request);
        Category categorySaved = categoryRepository.save(categoryToSave);
        return categoryMapper.toResponse(categorySaved);

     }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse) //We convert all categories to CategoryResponse
                .toList();
    }



    @Override
    public CategoryResponseDto getCategoryByName(String name){
        Category category = categoryRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Category with name " + name + "doesn't exist"));
        return categoryMapper.toResponse(category);
    }

}
