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
                product.getPrice().doubleValue(),
                (product.getCategories() != null)? product.getCategories().getName() : "Uncategorized",
                product.getCreatedAt()
        );


    }

    public Product toEntity(ProductRequestDTO dto){
        if (dto == null){
            return null;
        }

        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(BigDecimal.valueOf(dto.price()));
        return product;

    }
}
