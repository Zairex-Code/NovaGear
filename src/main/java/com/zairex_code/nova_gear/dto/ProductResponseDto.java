package com.zairex_code.nova_gear.dto;

import java.time.LocalDateTime;

public record ProductResponseDto(
        Long id,
        String name,
        Double price,
        String categoryName,
        LocalDateTime createdAt
) {

}
