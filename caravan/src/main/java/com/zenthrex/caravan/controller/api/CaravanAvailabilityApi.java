package com.zenthrex.caravan.controller.api;

import com.zenthrex.caravan.dtos.AvailableCaravanDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/caravans")
@Tag(name = "Caravans", description = "Caravan availability and booking operations")
public interface CaravanAvailabilityApi {

    @GetMapping("/available")
    @Operation(summary = "Get available caravans",
            description = "Returns a list of caravans available between the specified start and end dates. Caravans must not be booked or stopped during this period.")
    List<AvailableCaravanDto> getAvailableCaravans(
            @Parameter(description = "Start date of rental (inclusive)", example = "2025-08-01")
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

            @Parameter(description = "End date of rental (inclusive)", example = "2025-08-05")
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    );
}
