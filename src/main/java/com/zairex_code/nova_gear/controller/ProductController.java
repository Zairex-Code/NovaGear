package com.zairex_code.nova_gear.controller;

import com.zairex_code.nova_gear.dto.ProductRequestDTO;
import com.zairex_code.nova_gear.dto.ProductResponseDTO;
import com.zairex_code.nova_gear.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request){
        ProductResponseDTO response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
