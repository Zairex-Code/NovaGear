package com.zairex_code.nova_gear.mapper;

import com.zairex_code.nova_gear.dto.ProductResponseDTO;
import com.zairex_code.nova_gear.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class productMapper {

    public ProductResponseDTO toResponse(Product product){

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice().doubleValue(),
                (product.getCategories() != null)? product.getCategories().getName() : "Uncategorized",
                product.getCreatedAt()
        );


    }
}
