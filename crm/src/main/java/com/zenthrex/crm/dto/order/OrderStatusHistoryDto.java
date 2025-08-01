package com.zenthrex.crm.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Order status history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistoryDto {

    @Schema(description = "History ID")
    private Long id;

    @Schema(description = "From status")
    private String fromStatus;

    @Schema(description = "To status")
    private String toStatus;

    @Schema(description = "Change reason")
    private String reason;

    @Schema(description = "Notes")
    private String notes;

    @Schema(description = "Changed by user ID")
    private Long changedBy;

    @Schema(description = "Changed by user name")
    private String changedByName;

    @Schema(description = "Change date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime changedAt;
}
