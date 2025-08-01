package com.zenthrex.crm.dto.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Invoice template information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceTemplateDto {

    @Schema(description = "Template ID")
    private Long id;

    @Schema(description = "Template name")
    private String templateName;

    @Schema(description = "Template code")
    private String templateCode;

    @Schema(description = "Template content (HTML)")
    private String templateContent;

    @Schema(description = "Template styles (CSS)")
    private String templateStyles;

    @Schema(description = "Is default template")
    private Boolean isDefault;

    @Schema(description = "Is active")
    private Boolean isActive;

    @Schema(description = "Template description")
    private String description;

    @Schema(description = "Available variables")
    private String variables;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Updated date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @Schema(description = "Created by user ID")
    private Long createdBy;
}
