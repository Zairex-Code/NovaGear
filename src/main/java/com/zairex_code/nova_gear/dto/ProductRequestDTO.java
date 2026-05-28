package com.zairex_code.nova_gear.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record ProductRequestDTO(
        @NotBlank(message = "Product name is mandatory")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @Size(max = 255, message = "description length not more than 255 characters")
        String Description,

        @Positive(message = "Price must be greater than Zero")
        double price,

        @NotNull(message = "El stock es obligatorio")
        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stock,


        @URL(message = "The url format is no not valid. it must be a real link")
        String imageUrl,

        @NotNull(message = "Category Id is required")
        Long categoryId



) {
}
