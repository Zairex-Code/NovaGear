package com.zairex_code.nova_gear.service.impl;

import com.zairex_code.nova_gear.dto.product.ProductRequestDTO;
import com.zairex_code.nova_gear.dto.product.ProductResponseDTO;
import com.zairex_code.nova_gear.entity.Category;
import com.zairex_code.nova_gear.entity.Product;
import com.zairex_code.nova_gear.exception.ResourceNotFoundException;
import com.zairex_code.nova_gear.mapper.ProductMapper;
import com.zairex_code.nova_gear.repository.CategoryRepository;
import com.zairex_code.nova_gear.repository.ProductRepository;
import com.zairex_code.nova_gear.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + request.categoryId()));

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        
        return productMapper.toResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResponse);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId,pageable)
                .map(productMapper::toResponse);
    }
}
