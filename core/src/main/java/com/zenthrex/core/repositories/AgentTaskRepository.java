package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.AgentTask;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.TaskPriority;
import com.zenthrex.core.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgentTaskRepository extends JpaRepository<AgentTask, Long> {

    Page<AgentTask> findByAssignedToOrderByCreatedAtDesc(User assignedTo, Pageable pageable);

    @Query("""

            SELECT at FROM AgentTask at 
        WHERE at.assignedTo = :assignedTo
        AND (:status IS NULL OR at.status = :status)
        AND (:priority IS NULL OR at.priority = :priority)
        ORDER BY at.createdAt DESC
        """)
    Page<AgentTask> findTasksWithFilters(
            @Param("assignedTo") User assignedTo,
            @Param("status") TaskStatus status,
            @Param("priority") TaskPriority priority,
            Pageable pageable
    );

    Long countByAssignedToAndStatus(User assignedTo, TaskStatus status);

    List<AgentTask> findByAssignedToAndDueDateBeforeAndStatusNot(
            User assignedTo, LocalDateTime dueDate, TaskStatus status);

    @Query("""
        SELECT COUNT(at) FROM AgentTask at 
        WHERE at.assignedTo = :assignedTo 
        AND at.dueDate < CURRENT_TIMESTAMP 
        AND at.status NOT IN ('COMPLETED', 'CANCELLED')
        """)
    Long countOverdueTasks(@Param("assignedTo") User assignedTo);
}
