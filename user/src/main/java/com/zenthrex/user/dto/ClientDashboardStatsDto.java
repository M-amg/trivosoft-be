package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Client dashboard statistics")
public record ClientDashboardStatsDto(
        @Schema(description = "Total bookings")
        Integer totalBookings,

        @Schema(description = "Active bookings")
        Integer activeBookings,

        @Schema(description = "Total orders")
        Integer totalOrders,

        @Schema(description = "Total spent")
        BigDecimal totalSpent,

        @Schema(description = "Active listings (Pro clients only)")
        Integer activeListings,

        @Schema(description = "Total views (Pro clients only)")
        Integer totalViews,

        @Schema(description = "Revenue this month (Pro clients only)")
        BigDecimal monthlyRevenue,

        @Schema(description = "Booking trend")
        List<StatisticPointDto> bookingTrend,

        @Schema(description = "Spending trend")
        List<StatisticPointDto> spendingTrend,

        @Schema(description = "Favorite listings")
        List<FavoriteListingDto> favoriteListings
) {
}
