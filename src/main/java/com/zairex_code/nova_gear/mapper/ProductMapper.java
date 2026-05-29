package com.zairex_code.nova_gear.mapper;

import com.zairex_code.nova_gear.dto.ProductRequestDTO;
import com.zairex_code.nova_gear.dto.ProductResponseDTO;
import com.zairex_code.nova_gear.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {

    public ProductResponseDTO toResponse(Product product){

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice().doubleValue(),
                (product.getCategory() != null)? product.getCategory().getName() : "Uncategorized",
                product.getCreatedAt()
        );

    }

    public Product toEntity(ProductRequestDTO dto){
        if (dto == null){
            return null;
        }

        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(BigDecimal.valueOf(dto.price()));
        product.setStock(dto.stock());
        product.setImageUrl(dto.imageUrl());
        return product;

    }
}
