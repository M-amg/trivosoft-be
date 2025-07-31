package com.zenthrex.core.entites.file;

import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.FileStatus;
import com.zenthrex.core.enums.FileType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "file_metadata")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_filename", nullable = false)
    private String originalFilename;

    @Column(name = "stored_filename", nullable = false, unique = true)
    private String storedFilename;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_url")
    private String fileUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_type", nullable = false)
    private FileType fileType;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "checksum")
    private String checksum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by", nullable = false)
    private User uploadedBy;

    @Column(name = "entity_type")
    private String entityType; // USER, CARAVAN, ACCESSORY, etc.

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "storage_provider", nullable = false)
    private String storageProvider; // LOCAL, S3, AZURE

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}