package com.zenthrex.core.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(description = "Accessory image")
public class AccessoryImageDto {

    private Long id;

    @Schema(description = "Image URL", example = "https://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "Image title", example = "Front view")
    private String title;

    @Schema(description = "Display order", example = "1")
    private Integer displayOrder;

    @Schema(description = "Is primary image")
    private Boolean isPrimary;
}
