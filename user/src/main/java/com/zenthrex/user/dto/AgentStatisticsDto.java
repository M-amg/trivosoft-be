package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Agent statistics")
public record AgentStatisticsDto(
        @Schema(description = "Total assigned tasks")
        Integer totalTasks,

        @Schema(description = "Completed tasks")
        Integer completedTasks,

        @Schema(description = "Pending tasks")
        Integer pendingTasks,

        @Schema(description = "Overdue tasks")
        Integer overdueTasks,

        @Schema(description = "Profile verifications handled")
        Integer verificationsHandled,

        @Schema(description = "Pro upgrades processed")
        Integer proUpgradesProcessed,

        @Schema(description = "Average response time in hours")
        Double averageResponseTime,

        @Schema(description = "Client satisfaction rating")
        Double satisfactionRating,

        @Schema(description = "Performance trend")
        List<StatisticPointDto> performanceTrend,

        @Schema(description = "Task completion rate")
        Double completionRate
) {
}
