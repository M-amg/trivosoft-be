package com.zenthrex.accessory.controller.api;

import com.zenthrex.core.dtos.AccessoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@Tag(name = "Accessories", description = "Caravan accessory management")
@RequestMapping("/api/v1")
public interface AccessoryApi {

    @Operation(
            summary = "Create new accessory",
            description = "Create a new accessory listing. Requires SELLER or ADMIN role.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Accessory created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "403", description = "Insufficient permissions")
            }
    )
    @PostMapping("/sellers/accessories")
    ResponseEntity<AccessoryDto> createAccessory(
            @Parameter(description = "Accessory data", required = true)
            @Valid @RequestBody AccessoryDto accessoryDto
    );

    @Operation(
            summary = "Get accessory by ID",
            description = "Retrieve detailed information about a specific accessory"
    )
    @GetMapping("/accessories/{id}")
    ResponseEntity<AccessoryDto> getAccessory(
            @Parameter(description = "Accessory ID", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "List accessories",
            description = "Get paginated list of accessories with filtering options"
    )
    @GetMapping("/accessories")
    ResponseEntity<Page<AccessoryDto>> getAccessories(
            @Parameter(description = "Filter by category")
            @RequestParam(required = false) String category,

            @Parameter(description = "Filter by status")
            @RequestParam(required = false) String status,

            @Parameter(description = "Search in title and description")
            @RequestParam(required = false) String search,

            @Parameter(description = "Minimum price filter")
            @RequestParam(required = false) BigDecimal minPrice,

            @Parameter(description = "Maximum price filter")
            @RequestParam(required = false) BigDecimal maxPrice,

            @Parameter(description = "Filter by brand")
            @RequestParam(required = false) String brand,

            @Parameter(description = "Filter by stock availability")
            @RequestParam(required = false) Boolean inStock,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Update accessory",
            description = "Update accessory information. Requires SELLER or ADMIN role."
    )
    @PutMapping("/sellers/accessories/{id}")
    ResponseEntity<Void> updateAccessory(
            @Parameter(description = "Accessory ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Updated accessory data", required = true)
            @Valid @RequestBody AccessoryDto accessoryDto
    );

    @Operation(
            summary = "Delete accessory",
            description = "Remove accessory listing. Requires SELLER or ADMIN role."
    )
    @DeleteMapping("/sellers/accessories/{id}")
    ResponseEntity<Void> removeAccessory(
            @Parameter(description = "Accessory ID", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Search accessories",
            description = "Full-text search across accessory listings"
    )
    @GetMapping("/accessories/search")
    ResponseEntity<Page<AccessoryDto>> searchAccessories(
            @Parameter(description = "Search query", required = true)
            @RequestParam String query,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Update stock quantity",
            description = "Update accessory stock quantity. Requires SELLER or ADMIN role."
    )
    @PatchMapping("/sellers/accessories/{id}/stock")
    ResponseEntity<Void> updateStock(
            @Parameter(description = "Accessory ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "New stock quantity", required = true)
            @RequestParam Integer quantity
    );
}
