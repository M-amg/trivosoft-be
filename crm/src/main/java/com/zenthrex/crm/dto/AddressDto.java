package com.zenthrex.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Address information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @Schema(description = "Address line 1")
    private String addressLine1;

    @Schema(description = "Address line 2")
    private String addressLine2;

    @Schema(description = "City")
    private String city;

    @Schema(description = "State/Province")
    private String state;

    @Schema(description = "Postal code")
    private String postalCode;

    @Schema(description = "Country")
    private String country;
}
