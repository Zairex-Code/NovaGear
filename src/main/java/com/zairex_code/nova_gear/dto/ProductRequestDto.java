package com.zairex_code.nova_gear.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductRequestDto(
        @NotBlank(message = "Product name is mandatory")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @Positive(message = "Price must be greater than Zero")
        double price,

        @NotNull(message = "Category Id is required")
        Long categoryId
) {
}
