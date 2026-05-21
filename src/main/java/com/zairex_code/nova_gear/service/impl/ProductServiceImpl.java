package com.zairex_code.nova_gear.service.impl;

import com.zairex_code.nova_gear.dto.ProductRequestDTO;
import com.zairex_code.nova_gear.dto.ProductResponseDTO;
import com.zairex_code.nova_gear.entity.Category;
import com.zairex_code.nova_gear.entity.Product;
import com.zairex_code.nova_gear.mapper.ProductMapper;
import com.zairex_code.nova_gear.repository.CategoryRepository;
import com.zairex_code.nova_gear.repository.ProductRepository;
import com.zairex_code.nova_gear.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        Product product = productMapper.toEntity(request);

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.categoryId()));

        product.setCategories(category);

        Product savedProduct = productRepository.save(product);
        
        return productMapper.toResponse(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return null;
    }
}
