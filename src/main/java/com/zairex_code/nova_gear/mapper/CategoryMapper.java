package com.zairex_code.nova_gear.mapper;

import com.zairex_code.nova_gear.dto.category.CategoryRequestDTO;
import com.zairex_code.nova_gear.dto.category.CategoryResponseDto;
import com.zairex_code.nova_gear.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDto toResponse(Category category){
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt()
        );
    }


    public Category toEntity(CategoryRequestDTO dto){
        if (dto == null){
            return null;
        }

        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());

        return category;
    }
}
