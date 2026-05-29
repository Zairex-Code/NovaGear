package com.zairex_code.nova_gear.service;

import com.zairex_code.nova_gear.dto.product.ProductRequestDTO;
import com.zairex_code.nova_gear.dto.product.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO product);
    Page<ProductResponseDTO>getAllProducts(Pageable pageable);
    ProductResponseDTO getProductById(Long id);
    
}
