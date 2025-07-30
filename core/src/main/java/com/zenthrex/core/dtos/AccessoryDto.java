package com.zenthrex.core.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(description = "Accessory information")
public class AccessoryDto {

    private Long id;

    @Schema(description = "Accessory title", example = "Premium Caravan Awning")
    private String title;

    @Schema(description = "Detailed description")
    private String description;

    @Schema(description = "Price in euros", example = "299.99")
    private Double price;

    @Schema(description = "Delivery price", example = "15.00")
    private Double deliveryPrice;

    /**
     * Accessory category enum
     */
    public enum CategoryEnum {
        HITCHING_SECURITY("HITCHING_SECURITY"),
        EXTERIOR_EQUIPMENT("EXTERIOR_EQUIPMENT"),
        INTERIOR_FITTINGS("INTERIOR_FITTINGS"),
        ENERGY_AUTONOMY("ENERGY_AUTONOMY"),
        TRAVEL_TECHNOLOGY("TRAVEL_TECHNOLOGY"),
        MAINTENANCE_CLEANING("MAINTENANCE_CLEANING");

        private final String value;

        CategoryEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static CategoryEnum fromValue(String value) {
            for (CategoryEnum b : CategoryEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private CategoryEnum category;

    /**
     * Accessory status enum
     */
    public enum StatusEnum {
        AVAILABLE("AVAILABLE"),
        OUT_OF_STOCK("OUT_OF_STOCK"),
        DISCONTINUED("DISCONTINUED"),
        PENDING_APPROVAL("PENDING_APPROVAL"),
        REJECTED("REJECTED");

        private final String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String value) {
            for (StatusEnum b : StatusEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private StatusEnum status;

    @Schema(description = "Stock quantity", example = "50")
    private Integer stockQuantity;

    @Schema(description = "Brand name", example = "Thule")
    private String brand;

    @Schema(description = "Model", example = "Omnistor 9200")
    private String model;

    @Schema(description = "SKU", example = "THU-9200-001")
    private String sku;

    @Schema(description = "Weight in kg", example = "25.5")
    private Double weight;

    @Schema(description = "Dimensions", example = "400x250x20 cm")
    private String dimensions;

    @Schema(description = "Color", example = "Anthracite")
    private String color;

    @Schema(description = "Material", example = "Aluminum")
    private String material;

    @Schema(description = "Latitude for pickup/delivery")
    private Double latitude;

    @Schema(description = "Longitude for pickup/delivery")
    private Double longitude;

    @Schema(description = "Creation date")
    private LocalDateTime createdOn;

    @Schema(description = "Last update date")
    private LocalDateTime updatedOn;

    @Schema(description = "Seller information")
    private UserDto seller;

    @Schema(description = "Product images")
    @Valid
    private List<@Valid AccessoryImageDto> images;

    @Schema(description = "Customer reviews")
    @Valid
    private List<@Valid AccessoryReviewDto> reviews;

    @Schema(description = "Average rating", example = "4.5")
    private Double averageRating;

    @Schema(description = "Total review count", example = "25")
    private Integer reviewCount;
}


