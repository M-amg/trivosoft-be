package com.zenthrex.crm.dto.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Invoice sequence information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSequenceDto {

    @Schema(description = "Sequence ID")
    private Long id;

    @Schema(description = "Sequence name")
    private String sequenceName;

    @Schema(description = "Number prefix")
    private String prefix;

    @Schema(description = "Current number")
    private Long currentNumber;

    @Schema(description = "Increment by")
    private Integer incrementBy;

    @Schema(description = "Padding length")
    private Integer paddingLength;

    @Schema(description = "Reset yearly")
    private Boolean resetYearly;

    @Schema(description = "Last reset year")
    private Integer lastResetYear;

    @Schema(description = "Updated date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
