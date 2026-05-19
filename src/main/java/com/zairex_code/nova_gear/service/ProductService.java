package com.zairex_code.nova_gear.service;

import com.zairex_code.nova_gear.dto.ProductRequestDTO;
import com.zairex_code.nova_gear.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO product);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    
}
