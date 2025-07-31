package com.zenthrex.core.entites.notification;

import com.zenthrex.core.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_templates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String templateKey;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType;

    @Column(name = "email_subject")
    private String emailSubject;

    @Column(name = "email_template", columnDefinition = "TEXT")
    private String emailTemplate;

    @Column(name = "sms_template", columnDefinition = "TEXT")
    private String smsTemplate;

    @Column(name = "push_template", columnDefinition = "TEXT")
    private String pushTemplate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(columnDefinition = "TEXT")
    private String variables; // JSON string of available variables

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

