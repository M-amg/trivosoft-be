package com.zenthrex.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Customer information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @Schema(description = "Customer ID")
    private Long id;

    @Schema(description = "First name")
    private String firstName;

    @Schema(description = "Last name")
    private String lastName;

    @Schema(description = "Email address")
    private String email;

    @Schema(description = "Phone number")
    private String phone;
}