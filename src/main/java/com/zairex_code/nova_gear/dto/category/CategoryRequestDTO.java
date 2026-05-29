package com.zairex_code.nova_gear.dto.category;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        @NotBlank(message = "name is mandatory")
        String name,

        @Size(max = 255, message = "description must be less than 255 characters")
        String description
) {


}
