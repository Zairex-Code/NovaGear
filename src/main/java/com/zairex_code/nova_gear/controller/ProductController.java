package com.zairex_code.nova_gear.controller;

import com.zairex_code.nova_gear.dto.product.ProductRequestDTO;
import com.zairex_code.nova_gear.dto.product.ProductResponseDTO;
import com.zairex_code.nova_gear.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable){
        Page<ProductResponseDTO> response = productService.getAllProducts(pageable);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request){
        ProductResponseDTO response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductResponseDTO>> getProductsByCategory(@PathVariable Long categoryId ,
                                                                          @PageableDefault(size = 10, page = 0, sort = "id")Pageable pageable){
        Page<ProductResponseDTO> response = productService.getProductsByCategory(categoryId, pageable);
        return ResponseEntity.ok(response);
    }



}
