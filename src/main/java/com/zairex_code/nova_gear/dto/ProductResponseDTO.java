package com.zairex_code.nova_gear.dto;

import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String name,
        Double price,
        String categoryName,
        LocalDateTime createdAt
) {

}
