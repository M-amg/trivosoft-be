package com.zenthrex.crm.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Create address request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequest {

    @Schema(description = "Address line 1")
    @NotBlank(message = "Address line 1 is required")
    private String addressLine1;

    @Schema(description = "Address line 2")
    private String addressLine2;

    @Schema(description = "City")
    @NotBlank(message = "City is required")
    private String city;

    @Schema(description = "State/Province")
    private String state;

    @Schema(description = "Postal code")
    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @Schema(description = "Country")
    @NotBlank(message = "Country is required")
    private String country;
}