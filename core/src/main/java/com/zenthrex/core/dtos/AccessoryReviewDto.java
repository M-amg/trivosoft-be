package com.zenthrex.core.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(description = "Accessory review")
public class AccessoryReviewDto {

    private Long id;

    @Schema(description = "Rating from 1 to 5", example = "4")
    private Integer rating;

    @Schema(description = "Review title", example = "Great product!")
    private String title;

    @Schema(description = "Review comment")
    private String comment;

    @Schema(description = "Reviewer information")
    private UserDto reviewer;

    @Schema(description = "Review date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;

    @Schema(description = "Is verified purchase")
    private Boolean isVerifiedPurchase;
}
