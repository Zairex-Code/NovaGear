package com.zairex_code.nova_gear.dto.category;

import java.time.LocalDateTime;

public record CategoryResponseDto(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}
