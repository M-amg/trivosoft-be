package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Favorite listing summary")
public record FavoriteListingDto(
        @Schema(description = "Listing ID")
        Long id,

        @Schema(description = "Listing title")
        String title,

        @Schema(description = "Listing type")
        String type,

        @Schema(description = "Price")
        BigDecimal price,

        @Schema(description = "Image URL")
        String imageUrl
) {
}
