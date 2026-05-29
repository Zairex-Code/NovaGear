package com.zairex_code.nova_gear.service;


import com.zairex_code.nova_gear.dto.category.CategoryRequestDTO;
import com.zairex_code.nova_gear.dto.category.CategoryResponseDto;
import com.zairex_code.nova_gear.dto.product.ProductRequestDTO;
import com.zairex_code.nova_gear.dto.product.ProductResponseDTO;
import com.zairex_code.nova_gear.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDTO dto);
    CategoryResponseDto getCategoryById(Long id);
    CategoryResponseDto getCategoryByName(String name);
    List<CategoryResponseDto> getAllCategories();
}
